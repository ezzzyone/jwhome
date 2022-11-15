console.log("study1 con");

// java class 선언 방법
// class명 참조변수명 = new 생성자();
// 참조변수명.변수명 or 메서드명

const car = { color: "red", brand: "Benz", price: 3000 };
const car2 = {
    color: "white", brand: "BMW", move: function () {
        console.log("function move");
        console.log(this.color, this.brand);
    }
};

console.log("Car: ", car);
console.log("Car color: ", car.color);
console.log("Car brand: ", car["brand"]);
console.log("Car price: ", car.price);

console.log("Car2: ", car2);

console.log("Car2 type: ", typeof car2);
console.log("Type: ", typeof 3);

car2.move();