public class NBody {
    public static void main(String[] args){
        double T,dt;
        T = Double.parseDouble(args[0]);
        dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Body[] Bodies = readBodies(filename);

        In in = new In(filename);

        String imageToDraw = "images/starfield.jpg";

        StdDraw.setScale(-radius,radius);
        StdDraw.clear();
        StdDraw.enableDoubleBuffering();


        StdDraw.show();

        for(double t = 0; t < T; t += dt){
            double[] xForces = new double[Bodies.length];
            double[] yForces = new double[Bodies.length];
            int i = 0;
            for(Body b : Bodies){
                xForces[i] = b.calcNetForceExertedByX(Bodies);
                yForces[i] = b.calcNetForceExertedByY(Bodies);
                i++;
            }
            i = 0;
            for(Body b : Bodies){
                b.update(dt, xForces[i], yForces[i]);
                i++;
            }

            StdDraw.picture(0,0,imageToDraw);

            for(Body b : Bodies){
                b.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", Bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < Bodies.length; i++) {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                      Bodies[i].xxPos, Bodies[i].yyPos, Bodies[i].xxVel,
                      Bodies[i].yyVel, Bodies[i].mass, Bodies[i].imgFileName);   
        }
    }

    public static double readRadius(String filename){
        In in = new In(filename);
        in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Body[] readBodies(String filename){
        In in = new In(filename);
        int length = in.readInt();
        Body[] BArr = new Body[length];

        in.readDouble();

        for(int i = 0; i < length; i++){
            Body b = new Body(in.readDouble(), in.readDouble(), in.readDouble(), 
                                in.readDouble(), in.readDouble(), in.readString());
            BArr[i] = b;
        }
        
        return BArr;
    }
}
