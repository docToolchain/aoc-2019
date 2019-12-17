
class Hello
{
    getGreetings(name : string) : string
    {
        return "Hello "+name+"!";
    }
}

let hello : Hello = new Hello();

console.log(hello.getGreetings("World"));
