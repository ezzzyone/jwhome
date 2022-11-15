console.log("study2 con");

// java 배열
// 같은 타입만 가능, 한번 선언하면 크기를 줄이거나 늘이지 ㅗㅁㅅ함
// 배열 선언시 크기 지정

const arr1 = [];

const arr2 = new Array();
arr2.push("123");
console.log("arr1: ", arr1);
console.log("arr2: ", arr2);


// java Class
// class 클래스명, 멤버변수, 멤버메서드, 생성자 선언

// JS class
//Car class 선언 : 멤버변수- brand, color 선언
//move 메서드 선언
class Car {
// 생성자 - constructor : class명과 동일해 이름 안씀.
//인스턴스를 생성하고 변수의 초기화
    name; //설정안하면 undifind
    name2="test";
    //#변수명 = Private 의미
    #gas="fuel";

    //
    static contury = "korea";

    //클래스내부에서만 쓸수잇음.
    static #gear = 4;

    constructor(brand, color,gas) {
        this.brand = brand;
        this.color = color;
        this.gas = gas;
    }
    // 메서드 선언 - 여러개 선언 가능
    move() {
        console.log(this.brand, " + ", this.color, this.#gas);//private fild
        console.log("Gear =>", Car.#gear);
    }
}

// Object, Instance(특정한 객체를 보고 클래스 만듬?)
let car1 = new Car("Audi", "red","oil");
console.log("CAR1: ", typeof car1);
console.log("CAR1: ", car1);
//멤버추가
car1.price=3000;
console.log("Price =>", car1.price);
console.log("Gas =>", car1.gas);
car1.move();
console.log("Countury =>", Car.contury); //클래스변수 

class Human{

    #name;
    #age;
    constructor(){}

    //getter
    get name (){
        //get keyword
        //getter는 return을 꼭 명시
        return this.#name;
    }


    //setter
    set name(name){
        this.#name = name;
    }

        info(){
            console.log(this.#name, this.#age);
        }
}

const h1 = new Human();

//setter 호출, 함수 호출 형식이 아님.
//변수명.setter/getter명 = 값, setter가 호출 됨
h1.name="Test";

const name = h1.name;
console.log("Name =>", name);

h1.info();
