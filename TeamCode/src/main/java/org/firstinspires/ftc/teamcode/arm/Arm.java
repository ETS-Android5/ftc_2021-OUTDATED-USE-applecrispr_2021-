package org.firstinspires.ftc.teamcode.arm;

import java.sql.Array;

public class Arm {

    //segments
    public Segment[] segments;
    //base of arm
    public Vector base = new Vector();

    public Vector target = new Vector();

    public Arm(int segmentNum, int segmentLength){

        //initialize arm segments
        initSegments(segmentNum, segmentLength);
    }
    public void initSegments(int num, int length){
        segments = new Segment[num];
        for(int i = 0; i < num; i++){
            segments[i] = new Segment(length);
            if(i>0){
                segments[i-1].child = segments[i];
            }
        }
    }

    public void setTarget(Vector position){
        target = position;
    }

    public void update(){

        segments[segments.length-1].follow(target);
        for(int i = segments.length-2; i >= 0; i--){
            segments[i].follow();
        }

        segments[0].setA(base);
        for(int i = 1; i < segments.length; i++){
            segments[i].setA(segments[i-1].b);
        }

        for(int i = 0; i < segments.length; i++){
            segments[i].update();
        }
    }
}



/**
 *
 * var canv = document.getElementById('canv');
 * var ctx = canv.getContext('2d');
 *
 * canv.width = window.innerWidth;
 * canv.height = window.innerHeight;
 *
 * document.body.style.margin = 0;
 * document.body.style.overflow = "hidden";
 *
 * class Vector{
 *   constructor(x, y){
 *     this.x = x??0;
 *     this.y = y??0;
 *   }
 *   set(x, y){
 *     this.x = x;
 *     this.y = y;
 *   }
 *   heading(){
 *     return Math.atan2(this.y, this.x);
 *   }
 *   mult(val){
 *     this.x*=val;
 *     this.y*=val;
 *   }
 *   magnitude(){
 *     return Math.sqrt(this.x * this.x + this.y * this.y);
 *   }
 *   setMag(mag){
 *     var Mag = this.magnitude();
 *     this.x = this.x * mag / Mag;
 *     this.y = this.y * mag / Mag;
 *   }
 *   copy(){
 *     return new Vector(this.x, this.y);
 *   }
 *   static add(v1,v2){
 *     return new Vector(v1.x+v2.x, v1.y+v2.y);
 *   }
 *   static sub(v1,v2){
 *     return new Vector(v1.x-v2.x, v1.y-v2.y);
 *   }
 * }
 *
 * class Segment{
 *   constructor(){
 *     this.a = new Vector(canv.width/2, canv.height/2);
 *     this.b = new Vector();
 *     this.len = len;
 *     this.angle = 0;
 *     this.showAngle = this.angle;
 *     this.color = 'rgb('+Math.random()*255+','+Math.random()*255+','+Math.random()*255+')';
 *     this.calculateB();
 *   }
 *   follow(tx,ty){
 *     if(typeof ty=='undefined'){
 *       let targetX = this.child.a.x;
 *       let targetY = this.child.a.y;
 *       this.follow(targetX, targetY);
 *     }else{
 *       let target = new Vector(tx, ty);
 *       let dir = Vector.sub(target, this.a);
 *       this.angle = dir.heading();
 *
 *       dir.setMag(this.len);
 *       dir.mult(-1);
 *       this.a = Vector.add(target, dir);
 *     }
 *   }
 *   calculateB() {
 *     let dx = this.len * Math.cos(this.angle);
 *     let dy = this.len * Math.sin(this.angle);
 *     this.b.set(this.a.x + dx, this.a.y + dy);
 *   }
 *   setA(vec){
 *     this.a = vec;
 *     this.calculateB();
 *   }
 *   update(){
 *     this.calculateB();
 *
 *     //draw line
 *     ctx.strokeStyle = this.color;
 *     ctx.beginPath();
 *     ctx.moveTo(this.a.x, this.a.y);
 *     ctx.lineTo(this.b.x, this.b.y);
 *     ctx.stroke();
 *
 *     if(this.child){
 *       this.child.showAngle = this.child.angle-this.angle;
 *     }
 *
 *     ctx.font = '10px serif';
 *     ctx.fillStyle = "white";
 *     ctx.fillText(Math.round(radToDeg(this.showAngle)), this.a.x, this.a.y);
 *     this.showAngle = this.angle;
 *   }
 * }
 *
 * var fps = 60;
 * var segments = [];
 *
 * var len = 100;
 * var num = 2;
 *
 * initSegments(num);
 *
 * var mx = 0;
 * var my = 0;
 * var base = new Vector(canv.width/2,canv.height/2);
 *
 * setInterval(update, 1000/fps);
 *
 * function update(){
 *   ctx.fillStyle = "black";
 *   ctx.fillRect(0,0,canv.width,canv.height);
 *
 *   segments[segments.length-1].follow(mx,my);
 *   for(var i = segments.length-2; i >= 0; i--){
 *     segments[i].follow();
 *   }
 *
 *   segments[0].setA(base);
 *   for(var i = 1; i < segments.length; i++){
 *     segments[i].setA(segments[i-1].b);
 *   }
 *
 *   for(var i = 0; i < segments.length; i++){
 *     segments[i].update();
 *   }
 * }
 *
 * function mousePos(event){
 *   mx = event.clientX;
 *   my = event.clientY;
 * }
 *
 * function initSegments(num){
 *   for(var i = 0; i < num; i++){
 *     segments.push(new Segment());
 *     if(i>0){
 *       segments[i-1].child = segments[i];
 *     }
 *   }
 * }
 *
 * function sizeInput(event){
 *   document.getElementById('size-val').innerHTML = event.target.value;
 *   len = event.target.value;
 *   segments = [];
 *   initSegments(num);
 * }
 *
 * function segInput(event){
 *   document.getElementById('length-val').innerHTML = event.target.value;
 *   num = event.target.value;
 *   segments = [];
 *   initSegments(num);
 * }
 *
 * function radToDeg(radians)
 * {
 *   var pi = Math.PI;
 *   return radians * (180/pi);
 * }
 *
 * */