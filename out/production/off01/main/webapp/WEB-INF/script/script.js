// F O R M  F I L E
//item
let uname = document.querySelector(".buy_itemName");
let uItemQuantity = document.querySelector(".buy_itemQuaintity");
//user
let uItemEmail = document.querySelector(".buy_userEmail");
let uPhone = document.querySelector(".buy_userPhone");
let uNameUser = document.querySelector(".buy_userName");
let uSurname = document.querySelector(".buy_userSurname");
//adress
let ucity = document.querySelector(".buy_adressCity");
let uhouse = document.querySelector(".buy_adressHouse");
let uZip = document.querySelector(".buy_adressZip");
//price
let uDiscountCode = document.querySelector(".buy_discountCode");
let uItemPrice = document.querySelector(".buy_itemPrice");
let uItemCode = document.querySelector(".buy_smsCode");

let user = "";



$(".buy_sender").click(async function (e) {
  let msg = "";
  let phoneStr = uPhone.value.toString();
  let nameStr = uname.value.toString();
  let surnameStr = uSurname.value.toString();
  let cityStr = ucity.value.toString();
  let houseStr = uhouse.value.toString();
  let zipStr = uZip.value.toString();
  let itemQuantity = uItemQuantity.value.toString();
  let itemEmail = uItemEmail.value.toString();

  let phone = phoneStr.length === 9 && checkString(phoneStr) ? true : msg += "-incorrect nb \n <br>";
  let name = !checkString(nameStr) && nameStr.length > 0 ? true : msg += "-incorrect name \n <br>";
  let surname = !checkString(surnameStr) && surnameStr.length > 0 ? true : msg += "-incorrect surname \n <br>";
  let city = !checkString(cityStr) && cityStr.length > 0 ? true : msg += "-incorrect city \n <br>";
  let house = houseStr.length > 0 && checkString(phoneStr) ? true : msg += "-incorrect house nb \n <br>";
  let zip = zipStr.length === 6 ? true : msg += "-incorrect Zip code. Pattern __-___ \n <br>";
  //loadAccessToken();
  let x;
  let as = await checkCode().then((res) => {
    if (res !== "1") {
      console.log(res);
      msg += "-wrong sms Code <br>";
    }// Wartość x jest ustawiona na res
  });


  if (msg.length === 0) {
    createPay();

  } else {
    $('.buy_formInfo').html(msg);
    msg = "";
  }
});

var accessToken ="";
function checkString(string) {
  return /^[0-9]*$/.test(string);
}

$(document).on('keyup', '.buy_Zip', function (e) {
  let code = $(this).val();
  var key = event.keyCode || event.charCode;

  if ($(this).val().length == 2) {
    if (key == 8 || key == 46) {
    } else {
      $(this).val(code + '-');
    }
  }
  if ($(this).val().indexOf('--') !== -1) {
    $(this).val(code.replace('--', '-'));
  }
});
// F O R M  F I L E
$(".buy_discountBtn").click(function () {
  $.ajax({
    type: "POST",
    url: "http://localhost:8081/compare",
    data: {
      discountCode: uDiscountCode.value.toString(),
    },
    success: function (result) {
      if(result==="true")
      {
        changePrice();
      }
    },
    error: function (error) {
      console.error("Ajax request failed: " + error);
    }
  });
});

function changePrice()
{
  var newPrice = uItemPrice.textContent * 0.7;
  console.log(newPrice);
  uItemPrice.innerHTML=newPrice.toString();
}



function createPay() {
  $.ajax({
    type: "POST",
    url: "http://localhost:8081/payuPay",
    data: {
        itemName: uname.value.toString(),
        itemQuantity: uItemQuantity.value.toString(),

        userEmail : uItemEmail.value.toString(),
        userPhone : uPhone.value.toString(), //
        userName : uNameUser.value.toString(),
        userSurname : uSurname.value.toString(),

        adressCity : ucity.value.toString(),
        adressHouse : uhouse.value.toString(),
        adressZip : uZip.value.toString(),

        itemPrice : uItemPrice.textContent,
    },
    success: function (result) {
      console.log(result);
      window.location.href = result;
    },
    error: function (error) {
      console.error("Ajax request failed: " + error);
    }
  });
};

$(".buy_smsBtn").click(function () {
  $.ajax({
    type: "POST",
    url: "http://localhost:8081/smsApi",
    data: {
      // generateCode: "6669666",
    },
    success: function (result) {
      console.log(result);
    },
    error: function (error) {
      console.error("Ajax request failed: " + error);
    }
  });
});

function checkCode() {
  return new Promise((resolve, reject) => {
    $.ajax({
      type: "POST",
      url: "http://localhost:8081/smsApi",
      data: {
        generateCode: uItemCode.value.toString(),
      },
      success: function (result) {
        resolve(result); // Rozwiązanie obietnicy z odpowiedzią
      },
      error: function (error) {
        console.error("Ajax request failed: " + error);
        reject(error); // Odrzucenie obietnicy w przypadku błędu
      }
    });
  });
}
function ifCodeCorrectly(res)
{
 let msg="";
 return msg=res==="1"?"":"WRONG SMS CODE";
}

