public class RDtoWGS84 {

public static String RDToWGS84 (int x, int y) {
    //    Latitude and Longitude
    // The city "Amsterfoort" is used as reference.
    int referenceRdX = 155000; // "Rijksdriehoek" coordinate
    int referenceRdY = 463000;
    double referenceWgs84X = 52.15517; //"WGS84" coordinate
    double referenceWgs84Y = 5.387206;

    double dX = (x - referenceRdX) * Math.pow(10, -5);
    double dY = (y - referenceRdY) * Math.pow(10, -5);

    double sumN =
            (3235.65389 * dY) +
            (-32.58297 * dX * dX) +
            (-0.2475 * dY * dY) +
            (-0.84978 * dX * dX * dY) +
            (-0.0655 * Math.pow(dY, 3)) +
            (-0.01709 * dX * dX * dY * dY) +
            (-0.00738 * dX) +
            (0.0053 * Math.pow(dX, 4)) +
            (-0.00039 * dX * dX * Math.pow(dY, 3)) +
            (0.00033 * Math.pow(dX, 4) * dY) +
            (-0.00012 * dX * dY);
    double sumE =
            (5260.52916 * dX) +
            (105.94684 * dX * dY) +
            (2.45656 * dX * dY * dY) +
            (-0.81885 * Math.pow(dX, 3)) +
            (0.05594 * dX * Math.pow(dY, 3)) +
            (-0.05607 * Math.pow(dX, 3) * dY) +
            (0.01199 * dY) +
            (-0.00256 * Math.pow(dX, 3) * dY * dY) +
            (0.00128 * dX * Math.pow(dY, 4)) +
            (0.00022 * dY * dY) +
            (-0.00022 * dX * dX) +
            (0.00026 * Math.pow(dX, 5));

    double latitude = referenceWgs84X + (sumN / 3600);
    double longitude = referenceWgs84Y + (sumE / 3600);

    // Input
    // x = 122202
    // y = 487250
    //
    // Result
    // "52.372143838117, 4.90559760435224"
    return "latitude: " + latitude + "; longitude: " + longitude;
}
    
public static String convertRDToWGS84 (int rdX, int rdY) {
        double x0 = 155000.000,
            y0 = 463000.000,
            f0 = 52.156160556,
            l0 = 5.387638889,
            a01 = 3236.0331637, b10 = 5261.3028966,
            a20 = -32.5915821, b11 = 105.9780241,
            a02 = -0.2472814, b12 = 2.4576469,
            a21 = -0.8501341, b30 = -0.8192156,
            a03 = -0.0655238, b31 = -0.0560092,
            a22 = -0.0171137, b13 = 0.0560089,
            a40 = 0.0052771, b32 = -0.0025614,
            a23 = -0.0003859, b14 = 0.0012770,
            a41 = 0.0003314, b50 = 0.0002574,
            a04 = 0.0000371, b33 = -0.0000973,
            a42 = 0.0000143, b51 = 0.0000293,
            a24 = -0.0000090, b15 = 0.0000291;

        double dx = (rdX - x0) * Math.pow(10, -5);
        double dy = (rdY - y0) * Math.pow(10, -5);

        double df = a01 * dy + a20 * Math.pow(dx, 2) + a02 * Math.pow(dy, 2) + a21 * Math.pow(dx, 2) * dy + a03 * Math.pow(dy, 3);
               df += a40 * Math.pow(dx, 4) + a22 * Math.pow(dx, 2) * Math.pow(dy, 2) + a04 * Math.pow(dy, 4) + a41 * Math.pow(dx, 4) * dy;
               df += a23 * Math.pow(dx, 2) * Math.pow(dy, 3) + a42 * Math.pow(dx, 4) * Math.pow(dy, 2) + a24 * Math.pow(dx, 2) * Math.pow(dy,4);

        double f = f0 + df / 3600;

        double dl = b10 * dx + b11 * dx * dy + b30 * Math.pow(dx, 3) + b12 * dx * Math.pow(dy, 2) + b31 * Math.pow(dx, 3) * dy;
               dl += b13 * dx * Math.pow(dy, 3) + b50 * Math.pow(dx, 5) + b32 * Math.pow(dx, 3) * Math.pow(dy, 2) + b14 * dx * Math.pow(dy,4);
               dl += b51 * Math.pow(dx, 5) * dy + b33 * Math.pow(dx, 3) * Math.pow(dy, 3) + b15 * dx * Math.pow(dy, 5);

        double l = l0 + dl / 3600;

        double lat = f + (-96.862 - 11.714 * (f - 52) - 0.125 * (l - 5)) / 100000;
        double lon = l + (-37.902 + 0.329 * (f - 52) - 14.667 * (l - 5)) / 100000;

        return "latitude: " + lat + "; longitude: " + lon;
    }

}