package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

import java.lang.Math;
import java.util.Random;

public class Application extends Controller {

    private static int max = 10;
    private static int min = 0;
    private static Random rand = new Random();

    public static Result index() {


      int r = Math.abs(rand.nextInt()%max);

      session("random", ""+r);

      return ok(index.render(min, max, false, 0));
    }

    public static Result instructions() {
      return ok(instructions.render(min, max));
    }

    public static Result about() {
      return ok(about.render());
    }

    public static Result guess(String guess) {
      if (session("random") == null) {
        return redirect("/");
      }

      try {
        int guessInt = Integer.parseInt(guess);

        int r = Integer.parseInt(session("random"));

        int res = -1;
        if (guessInt > r) res = 1;
        else if (guessInt == r) {
          res = 0;
          session().clear();
        }

        return ok(index.render(min, max, true, res));
      } catch (Exception e) {
        return redirect("/");
      }
    }
}
