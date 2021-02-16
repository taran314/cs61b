public class NBody {

    public static double readRadius(String s) {

        In in = new In(s);
        int number_of_planets = in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String s) {

        In in = new In(s);
        // size of Planet[]
        int number_of_planets = in.readInt();
        // don't need the radius
        in.readDouble();
        // Delcare Planet array to be returned
        Planet[] planets = new Planet[number_of_planets];

        for (int i = 0; i < number_of_planets; i++) {

            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String imgs = in.readString();
            planets[i] = new Planet(xP, yP, xV, yV, m, imgs);
        }

        return planets;

    }

    public static void main(String[] args) {

        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);

        String filename = args[2];

        double radius = readRadius(filename);

        Planet[] allPlanets = readPlanets(filename);

        // Sets the co-ord scale for the universe to be the radius, x and y (box).

        StdDraw.setScale(-radius, radius);

        StdDraw.clear();

        // draws background image (x, y, filename) full path if not in folder
        StdDraw.picture(0, 0, "./images/starfield.jpg");

        for (Planet p : allPlanets) {
            p.draw();
        }

        // needed to create smooth animations
        StdDraw.enableDoubleBuffering();
        // time in program
        double t = 0;
        // number of planets
        int n = allPlanets.length;
        double[] xForces = new double[n];
        double[] yForces = new double[n];
        while (t < T) {

            for (int i = 0; i < n; i++) {
                xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
                yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
            }
            for (int i = 0; i < n; i++) {
                allPlanets[i].update(dt, xForces[i], yForces[i]);
            }
            // draw background
            StdDraw.picture(0, 0, "./images/starfield.jpg");

            // draw planets
            for (int i = 0; i < n; i++) {
                allPlanets[i].draw();
            }

            // displays drawing to screen
            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }
        // prints out the final state of the universe
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
}

    }



}