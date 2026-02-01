from typing import List, Tuple, Optional, Dict

import cv2
import numpy as np
from base_classes import  Detection

# ----------------------------
# Candidate detection function (dummy example)
# ----------------------------

def detect(image_path: str) -> List[Detection]:
    """
    Dummy detection:
    - Returns an empty list for all images.
    Replace this with the candidate's actual detection code.
    """
    # Example: parse file name to produce fake detections (demonstration only)
    image = cv2.imread(image_path, cv2.IMREAD_GRAYSCALE)

    f = 8
    image = cv2.resize(image, (int(image.shape[1]/f), int(image.shape[0]/f)))    

    # Simple blur to reduce noise
    gray = cv2.GaussianBlur(image, (7, 7), 1.5)


    # Hough Circle Transform with basic, fixed parameters
    circles = cv2.HoughCircles(
        image,
        cv2.HOUGH_GRADIENT_ALT,
        dp=2,
        minDist=20,
        param1=200,
        param2=0.2,
        minRadius=5,
        maxRadius=200
    )
    
    detections = []

    if circles is not None:
        circles = np.uint16(np.around(circles*f))
        for circle in circles[0,:]:
            x, y, r = circle
            det = Detection(cx=x, cy=y, r=r, cls=0)
            detections.append(det)

    return detections