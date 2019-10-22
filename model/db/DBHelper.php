<?php
Class DBHelper
  {
    private $connect;

    function __construct() {
      $hostname = 'localhost';
      $username = 'root';
      $password = '';
      $database = 'tvms';
      return  mysqli_connect($hostname, $username, $password, $database);
    }

    // Login
    public function logginDriver($username, $password)
    {
      $connect = $this->__construct();
      $result =  mysqli_query($connect, "SELECT * FROM driver WHERE driver_email = '$username' AND driver_password = '$password'");
      return mysqli_num_rows($result) > 0;
      mysqli_close($connect);
    }

    public function logginEnforcer($username, $password)
    {
      $connect = $this->__construct();
      $result =  mysqli_query($connect, "SELECT * FROM enforcer WHERE enforcer_email = '$username' AND enforcer_password = '$password'");
      return mysqli_num_rows($result) > 0;
      mysqli_close($connect);
    }


    public function driverByUsername($username){
      $connect = $this->__construct();
      return mysqli_fetch_all(mysqli_query($connect, "SELECT * FROM driver WHERE driver_email = '$username'"), MYSQLI_ASSOC);
      mysqli_close($connect);
    }

    public function enforcerByUsername($username){
      $connect = $this->__construct();
      return mysqli_fetch_all(mysqli_query($connect, "SELECT * FROM enforcer WHERE enforcer_email = '$username'"), MYSQLI_ASSOC);
      mysqli_close($connect);
    }

    // Create
    public function insertRecord($data, $fields, $table)
    {
        $ok;
        $fld = implode(',', $fields);
        $q = array();
        foreach ($data as $d) {
            $q[] = '?';
        }
        $plc = implode(',', $q);
        $sql = "INSERT INTO $table($fld) VALUES($plc)";
        try {
            $stmt = $this->conn->prepare($sql);
            $ok = $stmt->execute($data);
        } catch (PDOException $e) {
            echo $e->getMessage();
        }

        return $ok;
    }

    // Retrieve
    public function getAllRecord($table)
    {
        $rows;
        $sql = 'SELECT * FROM $table';
        try {
            $stmt = $this->conn->prepare($sql);
            $stmt->execute();
            $rows = $stmt->fetchAll(PDO::FETCH_ASSOC);
        } catch (PDOException $e) {
            echo $e->getMessage();
        }

        return $rows;
    }


   	public function getDriverUsername($data, $table){
   		$flag = false;
   		$sql = "SELECT * FROM $table WHERE driver_email = ?";
  		$stmt = $this->conn->prepare($sql);
  		$stmt->execute($data);
  		$row = $stmt->fetch(PDO::FETCH_ASSOC);
  		if($stmt->rowCount() > 0)
  		{
  			$flag = true;
  		}else
  		{
  			echo "<Script>alert('Error');</script>";
  		}

      // $this->conn = null;
     	return $flag;
   	}


   	// public function driverByUsername($data, $table){
   	// 	$flag = false;
   	// 	$row;
   	// 	$sql = 'SELECT * FROM $table WHERE driver_email = ?';
   	// 	try{
   	// 		$stmt = $this->conn->prepare($sql);
   	// 		$stmt->execute($data);
   	// 		$row = $stmt->fetch(PDO::FETCH_ASSOC);
   	// 		if($stmt->rowCount() > 0)
   	// 		{
   	// 			$flag = true;
   	// 		}else
   	// 		{
   	// 			echo "<Script>alert('Username Does not exist');</script>";
   	// 		}
   	// 	}catch(PDOException $e)
   	// 	{
   	// 		echo $e->getMessage();
   	// 	}
   	// 	return $row;
   	// }
}
