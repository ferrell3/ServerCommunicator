package Interfaces;

import Models.Request;
import Models.Results;

public interface IStringProcessor {

    Results toLowerCase(Request data);

    Results trim(Request input);

    Results parseInteger(Request input) throws NumberFormatException;

}
