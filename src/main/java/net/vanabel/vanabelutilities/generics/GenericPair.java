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

package net.vanabel.vanabelutilities.generics;

/**
 * A general class meant to emulate a key-value pair for use in ordered lists.
 * @param <S> Any Object
 * @param <T> Any Object
 */
public class GenericPair<S,T> {

    private S left;
    private T right;

    /**
     * Constructs a new GenericPair.
     * @param left The left Object (can be treated as the key of a Map entry)
     * @param right The right Object (can be treated as the value of a Map entry)
     */
    public GenericPair(S left, T right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Returns the left Object of the pair
     * @return The left Object (can be treated as the key of a Map entry)
     */
    public S getLeft() {
        return left;
    }

    /**
     * Returns the right Object of the pair.
     * @return The right Object (can be treated as the value of a Map entry)
     */
    public T getRight() {
        return right;
    }

    /**
     * Sets the left Object of the pair.
     * @param left The new left Object (can be seen as renaming the key of a Map entry)
     */
    public void setLeft(S left) {
        this.left = left;
    }

    /**
     * Sets the right Object of the pair.
     * @param right The new right Object (can be seen as replacing the value of a Map entry)
     */
    public void setRight(T right) {
        this.right = right;
    }

    /**
     * Returns the output of {@link Object#toString()}, with added information about the left and right Objects.
     * @return A String representation of the GenericPair
     */
    @Override
    public String toString() {
        return super.toString() + "[" + getLeft().toString() + "," + getRight().toString() + "]";
    }
}
