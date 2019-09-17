/*
 * Copyright (c) 2019 Vang "MusicScore" Ngo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package net.vanabel.vanabelutilities.numbers;

import net.vanabel.vanabelutilities.validator.ArrayValidator;
import net.vanabel.vanabelutilities.validator.ObjectValidator;

import java.util.*;

/**
 * A class that holds a collection of static methods that are used for common statistical calculations.
 */
public class Statistics {

    ////////////////////////////////////////
    //   Enums
    //////////////////////////////////////

    /**
     * Enumerated representations of the kinds of samples that one may deal with.
     */
    public enum SampleType {
        /**
         * Represents a limited sample which is composed of a small part of a population
         */
        SAMPLE,
        /**
         * Represents the whole population as a sample
         */
        POPULATION;
    }


    ////////////////////////////////////////
    //   Private static methods
    //////////////////////////////////////

    private static int getMidIndex(int size) {
        return size / 2 + (size % 2 != 0 ? 1 : 0);
    }

    private static double[] sortArray(final double... data) {
        ArrayValidator.notEmptyOrNull(data);
        double[] sortedArray = data.clone();
        Arrays.sort(sortedArray);
        return sortedArray;
    }


    ////////////////////////////////////////
    //   Public static methods
    //////////////////////////////////////

    /**
     * Returns the number at the first quadrant of the data set.
     * @param data The data set
     * @return The double at the first quadrant of the data
     */
    public static double getQuadrantI(final double... data) {
        ArrayValidator.notEmptyOrNull(data);
        int index = (data.length / 4) + 1;
        double[] sortedArray = sortArray(data);
        return (data.length / 2.0) % 2 != 0 ? sortedArray[index] : (sortedArray[index - 1] + sortedArray[index]) / 2;
    }

    /**
     * Returns the number at the third quadrant of the data set.
     * @param data The data set
     * @return The double at the third quadrant of the data
     */
    public static double getQuadrantIII(final double... data) {
        ArrayValidator.notEmptyOrNull(data);
        int index = ((data.length * 3) / 4) + 1;
        double[] sortedArray = sortArray(data);
        return (data.length / 2.0) % 2 != 0 ? sortedArray[index] : (sortedArray[index - 1] + sortedArray[index]) / 2;
    }

    /**
     * Returns the number with the largest value.
     * @param data The data set
     * @return The double with the largest value
     */
    public static double getMax(final double... data) {
        ArrayValidator.notEmptyOrNull(data);
        return sortArray(data)[data.length - 1];
    }

    /**
     * Returns the number with the smallest value.
     * @param data The data set
     * @return The double with the smallest value
     */
    public static double getMin(final double... data) {
        ArrayValidator.notEmptyOrNull(data);
        return sortArray(data)[0];
    }

    /**
     * Returns the median of a data set.
     * @param data The data set
     * @return The median of the data
     */
    public static double getMedian(final double... data) {
        ArrayValidator.notEmptyOrNull(data);
        int size = data.length;
        int midpoint = getMidIndex(size);
        double[] sortedArray = sortArray(data);
        return size % 2 != 0 ? sortedArray[midpoint] : (sortedArray[midpoint] + sortedArray[midpoint + 1]) / 2;
    }

    /**
     * Returns all modes of a data set.
     * If the data is uniformly distributed, this method returns null.
     * @param data The data set
     * @return A Set of all modes, or null if the data is uniformly distributed
     */
    public static Set<Double> getMode(final double... data) {
        ArrayValidator.notEmptyOrNull(data);

        Map<Double, Integer> modeMap = new HashMap<>();
        Set<Double> modes = new HashSet<>();
        int maxModeCount = 0;

        for (double datum : data) {
            if (modeMap.containsKey(datum)) {
                modeMap.replace(datum, modeMap.get(datum) + 1);
            }
            else {
                modeMap.put(datum, 1);
            }

            if (modeMap.get(datum) == maxModeCount && !modes.contains(datum)) {
                modes.add(datum);
            }
            else if (modeMap.get(datum) > maxModeCount) {
                modes.clear();
                modes.add(datum);
                maxModeCount = modeMap.get(datum);
            }
        }

        return modes.size() < data.length / maxModeCount ? modes : null;
    }

    /**
     * Returns the mean of a data set.
     * @param data The data set
     * @return The mean of the data
     */
    public static double getMean(final double... data) {
        ArrayValidator.notEmptyOrNull(data);

        double sum = 0;
        for (double datum : data) {
            sum += datum;
        }
        return sum;
    }

    /**
     * Returns the standard deviation of a data set.
     * @param sampleType The sample type. Cannot be null
     * @param data The data set
     * @return The standard deviation of the data set, while accounting for the sample type
     */
    public static double getStandardDeviation(SampleType sampleType, final double... data) {
        ObjectValidator.isNotNull(sampleType);
        ArrayValidator.notEmptyOrNull(data);

        double mean = getMean(data);
        double sigma = 0;
        for (double datum : data) {
            sigma += Math.pow(datum - mean, 2);
        }
        return Math.sqrt(sigma / (sampleType == SampleType.SAMPLE ? data.length - 1 : data.length));
    }
}
