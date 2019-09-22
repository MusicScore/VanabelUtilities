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

package net.vanabel.vanabelutilities.strings;

import net.vanabel.vanabelutilities.validator.ObjectValidator;

import java.util.*;

/**
 * A helper class to hold the parts of a split String from the various {@code splitString(...)} methods from
 * {@link StringUtils}.
 * <br><br>Depends on {@link ObjectValidator}.
 */
public class StringPartsData {

    private List<String> data;


    ////////////////////////////////////////
    //   Constructor
    //////////////////////////////////////

    /**
     * Constructs a new StringPartsData with an empty internal list of String parts.
     */
    StringPartsData() {
        data = new ArrayList<>();
    }

    /**
     * Constructs a new StringPartsData with a copy of the List of String parts.
     * @param strings A List of Strings to copy into the StringPartsData
     */
    StringPartsData(List<String> strings) {
        ObjectValidator.isNotNull(strings);
        data = new ArrayList<>(strings);
    }

    /**
     * Constructs a new StringSplitDataHolder with a copy of the array of String parts.
     * @param strings An array of strings to copy into the StringPartsData
     */
    StringPartsData(String... strings) {
        ObjectValidator.isNotNull(strings);

        data = new ArrayList<>();
        data.addAll(Arrays.asList(strings));
    }


    ////////////////////////////////////////
    //   Split string parts methods
    //////////////////////////////////////

    /**
     * Adds a new String to the end of the list of parts.
     * @param part The String to add
     */
    public void addPart(String part) {
        data.add(part);
    }

    /**
     * Adds a new String at the specified index of the list of parts.
     * @param index The index to add the new part at.
     *              <br>If the index is <b>less than 0</b>, then the part is added to the beginning of the list of
     *              parts.
     *              <br>If the index is <b>greater than or equal to the output of {@link #size()}</b>, then the part
     *              will be added to the end of the list of parts.
     * @param part The String to add
     */
    public void insertPart(int index, String part) {
        if (index <= 0) {
            data.add(0, part);
            return;
        }
        else if (index >= size()) {
            addPart(part);
            return;
        }
        data.add(index, part);
    }

    /**
     * Deletes a String at a given index of the list of parts.
     * @param index The index of the String to delete
     * @return True if the deletion was successful, false otherwise
     */
    public boolean removePart(int index) {
        if (index >= data.size()) {
            return false;
        }
        data.remove(index);
        return true;
    }

    /**
     * Deletes a String from the data, if the String exists in the data.
     * @param part The String to delete from the data
     * @return True if the deletion was successful, false otherwise
     */
    public boolean removePart(String part) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).equals(part)) {
                data.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the String at the given index of the list of parts.
     * @param index The index of the part to get
     * @return The String at the given index
     */
    public String getPart(int index) {
        return data.get(index);
    }

    /**
     * Deletes all empty Strings from the list of parts.
     */
    public void purgeEmptyParts() {
        List<String> newData = new ArrayList<>();
        for (String part : data) {
            if (!part.isEmpty()) {
                newData.add(part);
            }
        }
        data = newData;
    }


    ////////////////////////////////////////
    //   Collection-like methods
    //////////////////////////////////////

    /**
     * Returns the size of the list of parts.
     * @return The size of the list of parts
     */
    public int size() {
        return data.size();
    }


    ////////////////////////////////////////
    //   Conversion to other objects
    //////////////////////////////////////

    /**
     * Returns the list of parts as an ordered array.
     * @return A String array of all of the parts
     */
    public String[] asArray() {
        int size = data.size();
        String[] strArray = new String[size];
        for (int i = 0; i < size; i++) {
            strArray[i] = data.get(i);
        }
        return strArray;
    }

    /**
     * Returns the list of parts as a List of Strings.
     * @return A List of Strings containing all of the parts
     */
    public List<String> asList() {
        return data;
    }

    /**
     * Returns the list of parts as a Map, where each part is an entry.
     * @param separator The character to use as the separator between key and value in a given part
     * @return A Map where each part is an entry, and each entry's key is a String.
     *         <br>If the part does not have the specified character, then the entry's value will be null.
     *         <br>Otherwise, the entry's value will be the second half of the string after the specified character.
     */
    public Map<String, String> asMap(char separator) {
        Map<String, String> strMap = new HashMap<>();
        for (String str : data) {
            StringPartsData dataHolder = StringUtils.splitString(str, separator, 2);
            strMap.put(dataHolder.getPart(0), dataHolder.size() == 1 ? null : dataHolder.getPart(1));
        }
        return strMap;
    }


    ////////////////////////////////////////
    //   Split-to-single string methods
    //////////////////////////////////////

    /**
     * Returns every single part put together with nothing in between each part.
     * @return A String with every part in order, with nothing in between each part.
     *         <br>For example, if this StringPartsData has the parts {@code "Hello"}, {@code "world"}, and
     *         {@code " !"} in that order, then the output will be {@code "Helloworld !"}.
     */
    public String asSingleString() {
        if (size() == 1) {
            return data.get(0);
        }

        StringBuilder strBuilder = new StringBuilder();
        for (String str : data) {
            strBuilder.append(str);
        }
        return strBuilder.toString();
    }

    /**
     * Returns every single part as a single String, with a specified String {@code separator} in between each part.
     * @param separator The String to use to separate each part in the final output
     * @return A String with every part in order, with the specified additional String between each part.
     *         <br>For example, if this StringPartsData has the parts {@code "Hello"}, {@code "world"}, and
     *         {@code " !"} and the separator was {@code "==>"}, then the output will be {@code "Hello==>world==> !"}.
     */
    public String asSingleStringWithSeparator(String separator) {
        if (size() == 1) {
            return data.get(0);
        }

        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(data.get(0));
        for (int i = 1; i < data.size(); i++) {
            strBuilder.append(separator).append(data.get(i));
        }
        return strBuilder.toString();
    }

    /**
     * Returns every single part as a single String, with a specified character {@code separator} in between each part.
     * @param separator The character to use to separate each part in the final output
     * @return A String with every part in order, with the specified character between each part.
     *         <br>For example, if this StringPartsData has the parts {@code "Hello"}, {@code "world"}, and
     *         {@code " !"} and the separator was {@code 'x'}, then the output will be {@code "Helloxworldx !"}.
     */
    public String asSingleStringWithSeparator(char separator) {
        if (size() == 1) {
            return data.get(0);
        }

        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(data.get(0));
        for (int i = 1; i < data.size(); i++) {
            strBuilder.append(separator).append(data.get(i));
        }
        return strBuilder.toString();
    }
}
