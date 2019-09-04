package net.vanabel.vanabelutilities.strings;

import net.vanabel.vanabelutilities.validator.ObjectValidator;

import java.util.*;

/**
 * A helper class to hold the parts of a splitted String from the various {@code splitString(...)} methods from {@link StringUtils}.
 */
public class StringSplitDataHolder {

    private List<String> data;


    ////////////////////////////////////////
    //   Constructor
    //////////////////////////////////////

    /**
     * Constructs a new StringSplitDataHolder with an empty internal list of Strings.
     */
    StringSplitDataHolder() {
        data = new ArrayList<>();
    }

    /**
     * Constructs a new StringSplitDataHolder with a copy of the List of Strings.
     * @param strings A List of Strings to copy into the StringSplitDataHolder
     */
    StringSplitDataHolder(List<String> strings) {
        ObjectValidator.isNotNull(strings);
        data = new ArrayList<>(strings);
    }

    /**
     * Constructs a new StringSplitDataHolder with a copy of the array of Strings.
     * @param strings An array of strings to copy into the StringSplitDataHolder
     */
    StringSplitDataHolder(String... strings) {
        ObjectValidator.isNotNull(strings);

        data = new ArrayList<>();
        data.addAll(Arrays.asList(strings));
    }

    StringSplitDataHolder(Map<String, String> stringMap, char separator) {
        ObjectValidator.isNotNull(stringMap);

        data = new ArrayList<>();
        for (String strKey : stringMap.keySet()) {
            StringBuilder build = new StringBuilder();

            build.append(strKey);
            if (stringMap.get(strKey) == null) {
                continue;
            }
            build.append(separator).append(stringMap.get(strKey));
            data.add(build.toString());
        }
    }


    ////////////////////////////////////////
    //   Split string parts methods
    //////////////////////////////////////

    public void addPart(String part) {
        data.add(part);
    }

    public boolean removePart(int index) {
        if (index >= data.size()) {
            return false;
        }
        data.remove(index);
        return true;
    }

    public boolean removePart(String part) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).equals(part)) {
                data.remove(i);
                return true;
            }
        }
        return false;
    }

    public String getPart(int index) {
        return data.get(index);
    }


    ////////////////////////////////////////
    //   Collection-like methods
    //////////////////////////////////////

    public int size() {
        return data.size();
    }


    ////////////////////////////////////////
    //   Conversion to other objects
    //////////////////////////////////////

    public String[] asArray() {
        int size = data.size();
        String[] strArray = new String[size];
        for (int i = 0; i < size; i++) {
            strArray[i] = data.get(i);
        }
        return strArray;
    }

    public List<String> asList() {
        return data;
    }

    public Map<String, String> asMap(char separator) {
        Map<String, String> strMap = new HashMap<>();
        for (String str : data) {
            StringSplitDataHolder dataHolder = StringUtils.splitString(str, separator, 2);
            strMap.put(dataHolder.getPart(0), dataHolder.size() == 1 ? null : dataHolder.getPart(1));
        }
        return strMap;
    }


    ////////////////////////////////////////
    //   Splitted-to-single string methods
    //////////////////////////////////////

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
