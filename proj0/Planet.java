public class Planet {

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String imgs) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = imgs;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }
    // will be invoked by a planet: samh.calcDistance(rocinante);
    public double calcDistance(Planet p) {
        double xdist = this.xxPos - p.xxPos;
        double ydist = this.yyPos - p.yyPos;
        return Math.sqrt(xdist * xdist + ydist * ydist);

    }

    // will be invoked by a planet: samh.calcForceExertedBy(rocinante)
    public double calcForceExertedBy(Planet p) {
        double r  = this.calcDistance(p);
        return G * this. mass * p.mass / (r * r);

    }
    // want to calc the force and + or - dep on x and y coords
    public double calcForceExertedByX(Planet p) {
        double xdist = this.xxPos - p.xxPos;
        double F = this.calcForceExertedBy(p) * xdist / this.calcDistance(p);

        return F;
    }

    public double calcForceExertedByY(Planet p) {
        double ydist = this.yyPos - p.yyPos;
        double F = this.calcForceExertedBy(p) * ydist / this.calcDistance(p);

        return F;
    }

    // Planet[] allPlanets = {samh, rocinante, aegir};
    // samh.calcNetForceExertedByX(allPlanets);
    // samh.calcNetForceExertedByY(allPlanets);

    // Need to avoid including the planet itself in calculations.
    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double F = 0;
        for (Planet p : allPlanets) {
            if (!this.equals(p)) {
                F += this.calcForceExertedByX(p);
            }
        }

        return F;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double F = 0;
        for (Planet p : allPlanets) {
            if (!this.equals(p)) {
                F += this.calcForceExertedByY(p);
            }
        }
        return F;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / this.mass;
        double aY = fY / this.mass;
        this.xxVel += dt * aX;
        this.yyVel += dt * aY;
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;

    }

    public void draw() {

        StdDraw.picture(this.xxPos, this.yyPos, "./images/" + this.imgFileName);

    }

}
