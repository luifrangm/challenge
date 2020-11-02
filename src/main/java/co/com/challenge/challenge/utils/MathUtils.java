package co.com.challenge.challenge.utils;

import co.com.challenge.challenge.models.LineEquation;
import co.com.challenge.challenge.models.PointModel;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtils {



  private MathUtils() {}

  private static final int ZERO = 0;
  private static final int NEGATIVE_MODULE = -1;
  private static final int POWER_TWO = 2;
  private static final int DIVIDER_TWO = 2;
  private static final int DIVIDER_THREE = 3;
  private static final int DIVIDER_FIVE = 5;
  private static final int DIVIDER_SEVEN = 7;
  private static final int DIVIDER_ELEVEN = 11;


  public static float distanceBetweenTwoPoints(final PointModel pointA, final PointModel pointB) {
    final float componentOne = floatPow(pointA.getComponentX() - pointB.getComponentX(),POWER_TWO);
    final float componentTwo = floatPow(pointA.getComponentY() - pointB.getComponentY(),POWER_TWO);
    return floatSqr(componentOne + componentTwo);
  }

  public static LineEquation circumferenceEquation(final PointModel center, float radius) {
    final float x = POWER_TWO * center.getComponentX() * NEGATIVE_MODULE;
    final float y =
        POWER_TWO * center.getComponentY() * NEGATIVE_MODULE;
    final float ordered =
        floatPow(center.getComponentX(), POWER_TWO) +
            floatPow(center.getComponentY(), POWER_TWO) +
            (floatPow(radius,POWER_TWO) * NEGATIVE_MODULE);

    return LineEquation.builder()
        .componentX(x)
        .componentY(y)
        .ordered(ordered)
        .build();
  }

  public static  LineEquation addEquations(final LineEquation equationOne, final LineEquation equationTwo) {
    LineEquation negativeValues = changeSign(equationTwo);
    return simplifyFraction(
        LineEquation.builder()
            .componentX(equationOne.getComponentX() + negativeValues.getComponentX())
            .componentY(equationOne.getComponentY() + negativeValues.getComponentY())
            .ordered(equationOne.getOrdered() + negativeValues.getOrdered())
            .build());
  }

  private static LineEquation changeSign(LineEquation lineEquation) {
    lineEquation.setComponentX(
        lineEquation.getComponentX() * NEGATIVE_MODULE);
    lineEquation.setComponentY(
        lineEquation.getComponentY() * NEGATIVE_MODULE);
    lineEquation.setOrdered(
        lineEquation.getOrdered() * NEGATIVE_MODULE);
    return lineEquation;
  }

  private static LineEquation simplifyFraction(final LineEquation equation) {
    LineEquation simplifyEquation =
        equation;

    for(int i =0; i< 5; i++) {
      if(isDivisible(simplifyEquation,DIVIDER_TWO)) {
        simplifyEquation = applyDivide(simplifyEquation, DIVIDER_TWO);
      }

      if(isDivisible(simplifyEquation,DIVIDER_THREE)) {
        simplifyEquation = applyDivide(simplifyEquation, DIVIDER_THREE);
      }

      if(isDivisible(simplifyEquation,DIVIDER_FIVE)) {
        simplifyEquation = applyDivide(simplifyEquation, DIVIDER_FIVE);
      }

      if(isDivisible(simplifyEquation,DIVIDER_SEVEN)) {
        simplifyEquation = applyDivide(simplifyEquation, DIVIDER_SEVEN);
      }

      if(isDivisible(simplifyEquation,DIVIDER_ELEVEN)) {
        simplifyEquation = applyDivide(simplifyEquation, DIVIDER_ELEVEN);
      }
    }
    return simplifyEquation;
  }

  private static boolean isDivisible(final LineEquation simplifyEquation, final int divider) {
    return (divideNumber((int) simplifyEquation.getComponentX(),divider) &&
        divideNumber((int) simplifyEquation.getComponentY(),divider)
    );
  }

  private static LineEquation applyDivide(final LineEquation equation, final int divide) {
    return
        LineEquation.builder()
            .componentX(equation.getComponentX()/divide)
            .componentY(equation.getComponentY()/divide)
            .ordered(roundDecimalNumber(equation.getOrdered()/divide,1))
            .build();
  }

  private static boolean divideNumber(int number, int divider) {
    return
        number % divider == 0;
  }

  public static PointModel findPointX(
      final PointModel pointA, final float distanceA,
      final PointModel pointB, final float distanceB,
      final PointModel pointC, final float distanceC,
      final LineEquation finalEquation) {

    PointModel possiblePoint = PointModel.builder()
        .componentX(0)
        .componentY(
            roundDecimalNumber(
                (finalEquation.getOrdered() / finalEquation.getComponentY()) *
                    NEGATIVE_MODULE,1))
        .build();

    possiblePoint = initLineValue(
        possiblePoint,
        finalEquation.getComponentX() * NEGATIVE_MODULE,
        finalEquation.getComponentY());

    int count = 0;
    while (count < 10000) {
      possiblePoint.setComponentY(
          roundDecimalNumber(
              possiblePoint.getComponentY() +
                  (finalEquation.getComponentX() * NEGATIVE_MODULE),1));
      possiblePoint.setComponentX(
          roundDecimalNumber(
              possiblePoint.getComponentX() + finalEquation.getComponentY(),1));

      float calculatedDistanceA =
          roundDecimalNumber(
              distanceBetweenTwoPoints(pointA,possiblePoint), ZERO);
      float calculatedDistanceB =
          roundDecimalNumber(
              distanceBetweenTwoPoints(pointB,possiblePoint),ZERO);
      float calculatedDistanceC =
          roundDecimalNumber(
              distanceBetweenTwoPoints(pointC,possiblePoint),ZERO);

      if(compareDistance(calculatedDistanceA, roundDecimalNumber(distanceA,ZERO)) &&
          compareDistance(calculatedDistanceB, roundDecimalNumber(distanceB,ZERO)) &&
          compareDistance(calculatedDistanceC, roundDecimalNumber(distanceC,ZERO))) {
        return possiblePoint;
      }
      count ++;
    }
    return null;
  }

  private static boolean compareDistance(final float calculateDistance, final float distance) {
    final float tolerance = 3.0F;
    return
        calculateDistance - tolerance <= distance &&
            calculateDistance + tolerance >= distance;
  }

  private static float roundDecimalNumber(final float number, final int decimals) {
    BigDecimal bd = new BigDecimal(Float.toString(number));
    return
        bd.setScale(decimals, RoundingMode.HALF_EVEN).floatValue();
  }

  private static PointModel initLineValue(
      final PointModel possiblePoint,
      final float abcissa,
      final float slope) {

    float componentX = possiblePoint.getComponentX();
    float componentY = possiblePoint.getComponentY();

    for (int i=0; i< 5000; i++) {
      componentY = componentY - abcissa;
      componentX = componentX - slope;
    }
    return
        PointModel.builder()
            .componentY(componentY)
            .componentX(componentX)
            .build();
  }

  private static float floatPow(final float base, final int pow) {
    double doubleValue = Math.pow(base,pow);
    return (float) doubleValue;
  }

  private static float floatSqr(final float number) {
    double doubleValue = Math.sqrt(number);
    return (float) doubleValue;
  }

}


