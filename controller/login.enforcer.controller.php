<?php
	require_once '../model/model.php';

	$driver = new Model();
	$response = array();

	if($_SERVER['REQUEST_METHOD'] == 'POST')
	{
		if(isset($_POST['username']) && isset($_POST['password'])){
				if($driver->enforcerLogin($_POST['username'], $_POST['password']))
				{
					$datas = array($_POST['username'], $_POST['password']);
					$result = $driver->getEnforcerInfo($_POST['username']);
					$response['error'] = false;
					$response['message'] = 'Login Success';
					$response['enforcer_id'] = $result[0]['enforcer_id'];
					$response['enforcer_lname'] = $result[0]['enforcer_lname'];
					$response['enforcer_fname'] = $result[0]['enforcer_fname'];
					$response['enforcer_mi'] = $result[0]['enforcer_mi'];
					$response['enforcer_gender'] = $result[0]['enforcer_gender'];
					$response['enforcer_birthdate'] = $result[0]['enforcer_birthdate'];
					$response['enforcer_addressProv'] = $result[0]['enforcer_addressProv'];
					$response['enforcer_addressCity'] = $result[0]['enforcer_addressCity'];
					$response['enforcer_mobile'] = $result[0]['enforcer_mobile'];
					$response['enforcer_tel'] = $result[0]['enforcer_tel'];
					$response['enforcer_type'] = $result[0]['enforcer_type'];
					$response['enforcer_email'] = $result[0]['enforcer_email'];
					$response['enforcer_password'] = $result[0]['enforcer_password'];
					$response['agency_id'] = $result[0]['agency_id'];
				}else{
					$response['error'] = true;
					$response['message'] = 'Login Failed';
				}
			
		}else{
			$response['error'] = true;
			$response['message'] = 'INvalid Fields';
		}
	}else{
		$response['error'] = true;
		$response['message'] = 'INVALID REQUEST METHOD';
	}

	echo json_encode($response);