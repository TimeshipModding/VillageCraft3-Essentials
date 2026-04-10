package com.timeshipmodding.villagecraft3essentials.infrastructure.data.clientdata;

public class AtmClientData {
    public static int[] diamondToRuby = null;
    public static int[] diamondToAmber = null;
    public static int[] rubyToDiamond = null;
    public static int[] rubyToAmber = null;
    public static int[] amberToDiamond = null;
    public static int[] amberToRuby = null;

    public static int[] getDiamondToRuby() {
        return diamondToRuby;
    }

    public static int[] getDiamondToAmber() {
        return diamondToAmber;
    }

    public static int[] getRubyToDiamond() {
        return rubyToDiamond;
    }

    public static int[] getRubyToAmber() {
        return rubyToAmber;
    }

    public static int[] getAmberToDiamond() {
        return amberToDiamond;
    }

    public static int[] getAmberToRuby() {
        return amberToRuby;
    }

    public static void setRandomConversionRates(int[] diamondToRubySet, int[] diamondToAmberSet, int[] rubyToDiamondSet, int[] rubyToAmberSet, int[] amberToDiamondSet, int[] amberToRubySet) {
        diamondToRuby = diamondToRubySet;
        diamondToAmber = diamondToAmberSet;
        rubyToDiamond = rubyToDiamondSet;
        rubyToAmber = rubyToAmberSet;
        amberToDiamond = amberToDiamondSet;
        amberToRuby = amberToRubySet;
    }
}