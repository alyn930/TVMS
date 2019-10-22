<?php
	require_once 'db/DBHelper.php';

	Class Model extends DBHelper
	{

		public function __construct(){
			return DBHelper::__construct();
		}

		public function driverLogin($username,$password){
			return DBHelper::logginDriver($username, $password);
		}

		public function enforcerLogin($username,$password)
		{
			return DBHelper::logginEnforcer($username,$password);
		}

		public function registeDriver($data){
			return DBHelper::insertRecrod();
		}

		public function getAllDriver(){
			return DBHelper::getAllRecord();
		}

		public function getDriverByUsername($username){
			return DBHelper::getDriverUsername($username);
		}

		public function getDriverInfo($username){
			return DBHelper::driverByUsername($username);
		}

		public function getEnforcerInfo($username){
			return DBHelper::enforcerByUsername($username);
		}

	}