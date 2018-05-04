<html>
<script src="//cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.1/angular.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style>
.container-fluid
{
background-color: black;
}
.navbar-brand
{
.background-color: white;
}
p.sansserif {
    font-family: Arial, Helvetica, sans-serif;
}
.split {
  height: 50%;
  width: 50%;
  position: fixed;
  z-index: 1;
  top: 50%;
  overflow-x: hidden;
  padding-top: 50px;
}

.left {
  left: 0;
}


.right {
  right: 0;
 
}
.centered {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: left;
}
.form
{
	height:50%;
}
.fetch
{
	height:50%;
}
</style>
<body ng-app="myapp" ng-controller="gtscontroller">
<div class="form">
<form>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#"><p class="sansserif">Gemiro</p></a>
    </div>
  </div>
</nav>
<center>
USERACCOUNT_ID:<input type="text" name="useraccount_id" ng-model="useraccount.useraccount_id">
<input type="submit" value="getuseraccountdata" ng-click="getuseraccountdata(useraccount.useraccount_id)"/><br><br>
useraccount_id:<input type="text" name="useraccount_id" ng-model="accounttransaction.useraccount_id"><br><br>
transactionamount: <input type="text" name="transactionamount" ng-model="accounttransaction.transactionamount"><br><br>
transactiondate:<input type="text" name="transactiondate" ng-model="accounttransaction.transactiondate"><br><br>
transactiontype:<input type="text" name="transactiontype" ng-model="accounttransaction.transactiontype"><br><br>
 <input type="submit" value="Submit" ng-click="registeraccount()"/>
<h3>{{register}}</h3>
</center> 
</form>
</div>
 <div class="fetch">
<div class="split left">
  <div class="centered">
  <h3>Initial Table</h3>
USERACCOUNT_ID:<input type="text" ng-model="useraccount.useraccount_id"><br><br> 
USERACCOUNTBalance:<input type="text" ng-model="useraccount.balance"><br><br>
USERACCOUNTBalType:<input type="text" name="balancetype" ng-model="useraccount.useraccountbalancetype"><br><br> 
USER Name:<input type="text" ng-model="useraccount.gts_id.firstname"><br><br>
</div>
</div>
 <div class="split right">
  <div class="centered">
  	   <div class="centered">
<h3>post transaction table</h3><input type="submit" value="Submit" ng-click="getuseraccountdata1(useraccount1.useraccount_id)"/>
USERACCOUNT_ID:<input type="text" ng-model="useraccount1.useraccount_id"><br><br> 
USERACCOUNT Balance:<input type="text" ng-model="useraccount1.balance"><br><br>
USERACCOUNT Bal Type:<input type="text" name="balancetype" ng-model="useraccount1.useraccountbalancetype"><br><br> 
USER Name:<input type="text" ng-model="useraccount1.gts_id.firstname"><br><br>
</div> 
</div>
</div>
</body>
<script>
var app=angular.module("myapp",[]);
var glblaccountid;
app.controller("gtscontroller",function($scope,$http)
{
	$scope.useraccount={accountnumber:"",balance:"",gts_id:"",useraccount_id:glblaccountid,useraccountbalancetype:"",usertransaction_id:""}
	$scope.useraccount_id;
	
$scope.accounttransaction={transactionamount:"",transactiondate:"",transactiontype:"",useraccount_id:glblaccountid};
//$scope.accounttransaction.useraccount_id=$scope.useraccount.useraccount_id;
	$scope.registeraccount= function(useraccount_id){
		console.log('enter the data into useraccounttrans');
		$http.post('saveaccounttransaction',$scope.accounttransaction)
		.then(function(response)
				{
			$scope.accounttransaction=response.data;
			console.log("status",$scope.accounttransaction.status);
				console.log('Successfully registered');
				$scope.register="registered successfully....";
		});
			
		}
$scope.getuseraccountdata=function(useraccount_id){
		console.log('getuseraccountdata1');
		glblaccountid = useraccount_id;
		$http.get('getuseraccountlist/'+useraccount_id).then(function(response){
			$scope.useraccount=response.data;
		});	
	}
//$scope.useraccount.useraccount_id=accounttransaction.useraccount_id;
	$scope.getuseraccountdata1=function(useraccount_id){
		console.log('getuseraccountdata1');
		$http.get('getuseraccountlist/'+useraccount_id).then(function(response){
			$scope.useraccount1=response.data;
		});
	}

})

</script>

</html>

</html>
