// var MainImg = document.getElementById("MainImg");
// var smalling = document.getElementsByClassName("small-img");

// smalling[0].onclick = function () {
//   MainImg.src = smalling[0].src;
// };
// smalling[1].onclick = function () {
//   MainImg.src = smalling[1].src;
// };
// smalling[2].onclick = function () {
//   MainImg.src = smalling[2].src;
// };
// smalling[3].onclick = function () {
//   MainImg.src = smalling[3].src;
// };

// lấy thẻ <img> cho hình ảnh lớn
var largeImg = document.getElementById("MainImg");

// lấy danh sách các thẻ <img> cho các hình ảnh nhỏ
var smallImgList = document.querySelectorAll(".small-img");

// khai báo biến cho việc chuyển động hình ảnh
var opacity = 0;
var intervalID;

// lặp qua danh sách các thẻ <img> cho các hình ảnh nhỏ
for (var i = 0; i < smallImgList.length; i++) {
  // gán sự kiện nhấp chuột vào mỗi thẻ <img> cho hình ảnh nhỏ
  smallImgList[i].addEventListener("click", function() {
    // thay đổi thuộc tính "src" của thẻ <img> cho hình ảnh lớn bằng giá trị thuộc tính "src" của thẻ <img> cho hình ảnh nhỏ tương ứng
    largeImg.src = this.src;
    
    // đặt giá trị opacity của hình ảnh lớn bằng 0
    opacity = 0;
    
    // sử dụng setInterval để chuyển đổi hình ảnh lớn từng bước một
    intervalID = setInterval(function() {
      // tăng giá trị opacity của hình ảnh lớn lên 0.1
      opacity += 0.1;
      
      // nếu giá trị opacity vượt quá 1, dừng việc chuyển đổi hình ảnh lớn và xóa interval
      if (opacity > 1) {
        clearInterval(intervalID);
      }
      
      // thiết lập giá trị opacity cho hình ảnh lớn
      largeImg.style.opacity = opacity;
    }, 50);
  });
}

function incrementValue() {
  var value = parseInt(document.getElementById("number").value, 10);
  value = isNaN(value) ? 0 : value;
  value++;
  if (value > 99) return;
  document.getElementById("number").value = value;
}

function decrementValue() {
  var value = parseInt(document.getElementById("number").value, 10);
  value = isNaN(value) ? 0 : value;
  value--;
  if (value < 1) return;
  document.getElementById("number").value = value;
}
