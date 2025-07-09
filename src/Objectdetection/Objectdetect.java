package Objectdetection;

import org.opencv.core.*;
import org.opencv.videoio.VideoCapture;
import org.opencv.imgproc.Imgproc;
import org.opencv.highgui.HighGui;
import java.lang.System.*;

import java.util.ArrayList;
import java.util.List;

public class Objectdetect {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);



        VideoCapture capture = new VideoCapture(0);
        if (!capture.isOpened()) {
            System.out.println("Cannot open camera");
            return;
        }

        Mat frame = new Mat();
        Mat prevGray = new Mat();
        Mat gray = new Mat();
        Mat diff = new Mat();
        Mat thresh = new Mat();



        while (true) {
            capture.read(frame);
            if (frame.empty()) break;

            Imgproc.cvtColor(frame, gray, Imgproc.COLOR_BGR2GRAY);
            Imgproc.GaussianBlur(gray, gray, new Size(21, 21), 0);

            if (!prevGray.empty()) {
                Core.absdiff(prevGray, gray, diff);
                Imgproc.threshold(diff, thresh, 25, 255, Imgproc.THRESH_BINARY);
                Imgproc.dilate(thresh, thresh, new Mat(), new Point(-1, -1), 2);

                List<MatOfPoint> contours = new ArrayList<>();
                Imgproc.findContours(thresh, contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

                for (MatOfPoint contour : contours) {
                    if (Imgproc.contourArea(contour) < 500) continue;

                    Rect rect = Imgproc.boundingRect(contour);
                    Imgproc.rectangle(frame, rect, new Scalar(0, 255, 0), 2);
                    Imgproc.putText(frame, "Moving Object", new Point(rect.x, rect.y - 10), Imgproc.FONT_HERSHEY_SIMPLEX, 0.5, new Scalar(0, 255, 255), 1);
                }
            }




            HighGui.imshow("Motion Tracking", frame);
            if (HighGui.waitKey(1) == 27) break; // ESC to exit

            gray.copyTo(prevGray);
        }

        capture.release();
        HighGui.destroyAllWindows();
    }
}

