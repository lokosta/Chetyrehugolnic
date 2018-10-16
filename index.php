<?php 
$json2=file_get_contents('php://input');
$username="root";
$password="020860bk";
$outmsv=json_decode($json2,true);
$data=array();
$db_conn = new mysqli("localhost", "root", $password, "dom_molodiojy");
$tablename=$outmsv['Table_Name'];



switch($outmsv['Method']){
	case "GET":
	
	    $a=array('answer'=>"no login");
		if($outmsv['Table_Name']=="users"){//для проверки логина и пароля
		$login=$outmsv['login'];
		$res=mysqli_query($db_conn,"select * from users WHERE email='$login'");
		//echo mysqli_fetch_assoc($res);
		$rrr=mysqli_num_rows($res);
		if($rrr==0){
			echo $js=json_encode($a);}
		else{
		$re=mysqli_fetch_assoc($res);
		if($outmsv['password']==$re['_password_']){
			$a['answer']="ok";
			$a=$a+$re;
			echo $js=json_encode($a);}
		else{
			$a['answer']="no password";
		echo $js=json_encode($a);}}
	}
	else{//сброс списка меропритий или курсов
		$r=array("zapis"=>false);
		if($outmsv['Table_Name']=="courses"){
			$result  =  mysqli_query( $db_conn,  "SELECT * from ($tablename)" );}
		if($outmsv['Table_Name']=="events"){
			$result  =  mysqli_query( $db_conn,  "SELECT * from events" );}
		while ($row  =  mysqli_fetch_assoc($result)){
			$point=mysqli_query($db_conn,"SELECT lastname,firstname from users where user_id='$row[user_id]'");
			if($record=mysqli_query($db_conn,"SELECT course_id from records WHERE user_id='$row[user_id]')")){//----------------------random_id
				$r["zapis"]=true;}//true false
			//$r=mysqli_fetch_assoc($record);
			$p=mysqli_fetch_assoc($point);
			$x=$row+$p+$r;
			$data[]=$x;
		}
		$jrow=json_encode($data);
	echo $jrow;}
	//засунуть json во внутрь циклов
	break;
	
	case "SET"://регистрация
	$a=array("znachenie"=>false);
	$us=array();
	$us=$outmsv;
	if($res = mysqli_query($db_conn, "insert into users values('$us[user_id]','$us[group_id]','$us[email]',
	'$us[password]','$us[lastname]','$us[firstname]','$us[info]')")){
		$a["znachenie"]=true;
		}
		$js=json_encode($a);
	echo $js;
	//echo если запись успешна то "true" 
	//insert into dom_molodiojy.users values(4,3,"kvad@mat/egoradmail.ru","1234","filchuk","leonid","info")
	break;
	
	case 'UPDATE'://изменение учётной записи в личном кабинете
	$user=array();
	$user=$outmsv;
	$res = mysqli_query($db_conn, "update users set group_id='$user[group_id]', email='$user[email]',_password_='$user[password]',
	lastname='$user[lastname]',firstname='$user[firstname]',info='$user[info]' WHERE user_id='$user[user_id]'");
	break;
	
	case "Srec"://добавление/удаление записи на меропритие/курс
		if($outmsv['Table_Name']=="events"){
			$evid=$outmsv['random_id'];
			$search=mysqli_query($db_conn, "SELECT * from records WHERE (user_id=($usid) and event_id=($evid))");
			$count=mysqli_num_rows($search);
			if($count>0){
			$del=mysqli_query($db_conn, "DELETE from records WHERE (user_id=($usid) and event_id=($evid))");
			echo "udalenie";}
			else {
			$cre=mysqli_query($db_conn, "insert into records values(($usid),($evid),0)");
			echo "zapis";}}
		else{
			$curid=$outmsv['random_id'];
			$search=mysqli_query($db_conn, "SELECT * from records WHERE (user_id=($usid) and course_id=($curid))");
			$count=mysqli_num_rows($search);
			if($count>0){
			$del=mysqli_query($db_conn, "DELETE from records WHERE (user_id=($usid) and course_id=($curid))");
			echo "udalenie";}
			else {
			$cre=mysqli_query($db_conn, "insert into records values(($usid),0,($curid))");
			echo "zapis";}
		}
	break;

	case "Grec"://сброс списка курсов и меропритий на которые записан
		if ($outmsv['Table_Name']=="courses"){
		$usid=$outmsv['user_id'];
		$result  =  mysqli_query( $db_conn,  "SELECT * from records WHERE (user_id=($usid) and course_id!=0)");
		while ($row  = mysqli_fetch_assoc($result)){
			$point=mysqli_query($db_conn,"SELECT * from courses where course_id='$row[course_id]'");
			$count=mysqli_num_rows($point);
			$p=mysqli_fetch_assoc($point);
			$name=mysqli_query($db_conn,"SELECT lastname,firstname from Users where user_id='$p[user_id]'");
			$count1=mysqli_num_rows($name);
			$n=mysqli_fetch_assoc($name);
			$x=$p+$n;
			$data[]=$x;
		}}
		else{
			$usid=$outmsv['user_id'];
			$result  =  mysqli_query( $db_conn,  "SELECT * from records WHERE (user_id=($usid) and event_id!=0)");
		while ($row  =  mysqli_fetch_assoc($result)){
			$point=mysqli_query($db_conn,"SELECT * from events where event_id='$row[event_id]'");
			$p=mysqli_fetch_assoc($point);
			$name=mysqli_query($db_conn,"SELECT lastname,firstname from users where user_id='$p[user_id]'");
			$n=mysqli_fetch_assoc($name);
			$x=$p+$n;
			$data[]=$x;}
		}
		$jrow=json_encode($data);
		echo $jrow;
	break;
	
	default://вывод в браузер
	echo "курсы"."<br>";
	$result  =  mysqli_query( $db_conn,  "SELECT * FROM courses" );
	while ($row  =  mysqli_fetch_row($result)){
		$point=mysqli_query($db_conn,"SELECT lastname,firstname from Users where user_id='$row[2]'");
		$p=mysqli_fetch_row($point);
        echo $row[0]." ".$row[1]." ".$row[2]." ".$row[3]." ".$row[4]." ".$row[5]." ".$p[0]." ".$p[1]."<br>";
	}
    $out="read from database+111";
	echo "мероприятия"."<br>";
	$result  =  mysqli_query( $db_conn,  "SELECT * FROM events" );
	while ($row  =  mysqli_fetch_row($result)){
		$point=mysqli_query($db_conn,"SELECT lastname,firstname from Users where user_id='$row[2]'");
		$p=mysqli_fetch_row($point);
        echo $row[0]." ".$row[1]." ".$row[2]." ".$row[3]." ".$row[4]." ".$row[5]." ".$p[0]." ".$p[1]."<br>";
	}
	echo "пользователи"."<br>";
	$result  =  mysqli_query( $db_conn,  "SELECT * FROM users" );
	while ($row  =  mysqli_fetch_row($result)){
        echo $row[0]." ".$row[1]." ".$row[2]." ".$row[3]." ".$row[4]." ".$row[5]." "."<br>";
	}
}
mysqli_close($db_conn);
?>
