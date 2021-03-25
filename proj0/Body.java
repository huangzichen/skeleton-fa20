public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP,double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b){
        double dx = this.xxPos - b.xxPos;
        double dy = this.yyPos - b.yyPos;
        double r = Math.sqrt(Math.pow(dx,2) + Math.pow(dy,2));
        return r;
    }

    public double calcForceExertedBy(Body b){
        double r = this.calcDistance(b);
        double forceExertedByb = 6.67 * Math.pow(10,-11) * this.mass * b.mass / Math.pow(r, 2);
        return forceExertedByb;    
    }

    public double calcForceExertedByX(Body b){
        double dx = b.xxPos - this.xxPos;
        double r = this.calcDistance(b);
        double force = this.calcForceExertedBy(b);
        double forceX = force * dx / r;
        return forceX;
    }

    public double calcForceExertedByY(Body b){
        double dy = b.yyPos - this.yyPos;
        double r = this.calcDistance(b);
        double force = this.calcForceExertedBy(b);
        double forceY = force * dy / r;
        return forceY;
    }

    public double calcNetForceExertedByX(Body[] allBodys){
        double result = 0;
        for(Body b : allBodys){
            if(this.equals(b)){
                continue;
            }
            result += this.calcForceExertedByX(b);
        }
        return result;
    }

    public double calcNetForceExertedByY(Body[] allBodys){
        double result = 0;
        for(Body b : allBodys){
            if(this.equals(b)){
                continue;
            }
            result += this.calcForceExertedByY(b);
        }
        return result; 
    }

    public void update(double dt, double netForceX, double netForceY){
        double accX = netForceX / this.mass, accY = netForceY / this.mass;
        this.xxVel += accX * dt;
        this.yyVel += accY * dt;
        this.xxPos += xxVel * dt;
        this.yyPos += yyVel * dt;
        
    }

    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + imgFileName);
    }
}
