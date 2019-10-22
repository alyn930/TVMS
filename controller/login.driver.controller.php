<?php
	require_once '../model/model.php';

	$driver = new Model();
	$response = array();

	if($_SERVER['REQUEST_METHOD'] == 'POST')
	{
		if(isset($_POST['username']) && isset($_POST['password'])){
			$data = array($_POST['username'], $_POST['password']);
				if($driver->driverLogin($_POST['username'], $_POST['password']))
				{
					$datas = array($_POST['username'], $_POST['password']);
					$result = $driver->getDriverInfo($_POST['username']);
					$response['error'] = false;
					$response['message'] = 'Login Success';
					$response['driver_id'] = $result[0]['driver_id'];
					$response['license_id'] = $result[0]['license_id'];
					$response['driver_pincode'] = $result[0]['driver_pincode'];
					$response['driver_lname'] = $result[0]['driver_lname'];
					$response['driver_fname'] = $result[0]['driver_fname'];
					$response['driver_mi'] = $result[0]['driver_mi'];
					$response['driver_gender'] = $result[0]['driver_gender'];
					$response['driver_birthdate'] = $result[0]['driver_birthdate'];
					$response['driver_addressProv'] = $result[0]['driver_addressProv'];
					$response['driver_addressCity'] = $result[0]['driver_addressCity'];
					$response['driver_mobile'] = $result[0]['driver_mobile'];
					$response['driver_tel'] = $result[0]['driver_tel'];
					$response['driver_type'] = $result[0]['driver_type'];
					$response['driver_email'] = $result[0]['driver_email'];
					$response['driver_password'] = $result[0]['driver_password'];
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