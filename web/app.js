var app = angular.module('plunker', []);

app.controller('MainCtrl', function($scope) {

  var cleanAccount = function() {
    var account = {
      name: 'Md. Nazrul Islam',
      startingBalace: 100.00,
      runningBalance: 73.34
    }
    return account;
  };
  
   var cleanTransaction = function() {
    var transaction = {
      type: 'debit',
      amount: 0.00,
      description: ''
    }

    return transaction;
  };

  var transactions = [{
    amount: 100.00,
    description: 'Deposit',
    type: 'credit'
  }, {
    amount: 50.00,
    description: 'Best Buy',
    type: 'debit'
  }, {
    amount: 25.26,
    description: 'Olive Garden',
    type: 'debit'
  }, {
    amount: 100.00,
    description: 'Deposit',
    type: 'credit'
  }, {
    amount: 35.15,
    description: 'McPee\'s',
    type: 'debit'
  }, {
    amount: 16.25,
    description: 'QT205',
    type: 'debit'
  }, ];

 

  $scope.transaction = cleanTransaction();
  $scope.account = cleanAccount();

  $scope.transactionList = transactions;

  $scope.saveTransaction = function() {
    var amount = parseFloat($scope.transaction.amount);
    var num = parseFloat($scope.account.runningBalance);
    var answer = 0;
    if ($scope.transaction.type === 'credit') {
      answer = num + amount
    } else {
      answer = num - amount
    }
    $scope.account.runningBalance = answer;

    $scope.transaction.amount = amount;
    transactions.push($scope.transaction);
    $scope.transaction = cleanTransaction();
  };

});


//app.directive('moneywarn', function() {
//  var staticWarningLevel = .2;
//
//  return {
//    restrict: 'A',
//    scope: {
//      val: '=moneywarn'
//    },
//    link: function(scope, element, attrs) {
//      scope.$watch('val', function(newValue) {
//        var startBalance = parseInt(attrs.startbalance);
//        var warningLevel = startBalance * staticWarningLevel;
//        if (newValue === warningLevel) {
//          element.addClass('alert-warning');
//           element.removeClass('alert-danger');
//        } else if (newValue < warningLevel) {
//          element.addClass('alert-danger');
//        } else {
//          element.removeClass('alert-warning');
//          element.removeClass('alert-danger');
//        }
//
//      }, true);
//    }
//  }
//
//});