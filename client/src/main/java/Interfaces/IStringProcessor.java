package Interfaces;

import Models.Request;

public interface IStringProcessor {

    String toLowerCase(Request input);

    String trim(Request input);

    String parseInteger(Request input) throws NumberFormatException;
}
