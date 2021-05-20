package simpledb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Tuple maintains information about the contents of a tuple. Tuples have a
 * specified schema specified by a TupleDesc object and contain Field objects
 * with the data for each field.
 */

// schema = construction
//
public class Tuple implements Serializable {

    private static final long serialVersionUID = 1L;

    RecordId recordId = null;
    TupleDesc tupleDesc;

    Field[] fieldArr;

    /**
     * Create a new tuple with the specified schema (type).
     * 
     * @param td
     *            the schema of this tuple. It must be a valid TupleDesc
     *            instance with at least one field.
     */
    public Tuple(TupleDesc td) {
        tupleDesc = td;
        int len = td.TDItems.size();
        fieldArr = new Field[len];
    }

    /**
     * @return The TupleDesc representing the schema of this tuple.
     */
    public TupleDesc getTupleDesc() {
        return tupleDesc;
    }

    /**
     * @return The RecordId representing the location of this tuple on disk.
     *         Should return RecordId that was set with setRecordId(). May be null.
     */
    public RecordId getRecordId() {
        return recordId;
    }

    /**
     * Set the RecordId information for this tuple.
     * 
     * @param rid
     *            the new RecordId for this tuple.
     */
    public void setRecordId(RecordId rid) {
        recordId = rid;
    }

    /**
     * Change the value of the ith field of this tuple.
     * 
     * @param i
     *            index of the field to change. It must be a valid index.
     * @param f
     *            new value for the field.
     */
    public void setField(int i, Field f) {
        fieldArr[i] = f;
    }

    /**
     * @return the value of the ith field, or null if it has not been set.
     * 
     * @param i
     *            field index to return. Must be a valid index.
     */
    public Field getField(int i) {
        return fieldArr[i];
    }

    /**
     * Returns the contents of this Tuple as a string. Note that to pass the
     * system tests, the format needs to be as follows:
     * 
     * column1\tcolumn2\tcolumn3\t...\tcolumnN\n
     * 
     * where \t is any whitespace, except newline, and \n is a newline
     */
    public String toString() {
        String ans = "";
        int len = fieldArr.length;
        for (int i = 0; i < len; i++){
            if (i == len - 1){
                ans += fieldArr[i].toString() + '\n';
            }else {
                ans += fieldArr[i].toString() + '\t';
            }
        }
        return ans;
    }
    
    /**
     * @return
     *        An iterator which iterates over all the fields of this tuple
     * */
    public Iterator<Field> fields()
    {
        if (fieldArr.length > 0){
            return new Iterator<Field>() {
                int index = 0;
                @Override
                public boolean hasNext() {
                    return index < fieldArr.length;
                }

                @Override
                public Field next() {
                    index++;
                    return fieldArr[index - 1];
                }
            };
        } else return null;
    }
    
    /**
     * Reset the TupleDesc of this tuple
     * (Only affecting the TupleDesc, does not need to worry about fields inside the Tuple)
     * */
    public void resetTupleDesc(TupleDesc td)
    {
        tupleDesc = td;
    }
}
