package asset;

import Currency.CurrencyUSD;

/**
 * Asset is a class that represents an asset. The asset is used to evaluate if a client has collateral
 * when they request a loan.
 * It has:
 * assetName: the name of the asset
 * value: the value of the asset. It is in USD, so it is a CurrencyUSD type.
 */
public class Asset {
    private String assetName;
    private CurrencyUSD value;

    /**
     * Constructor
     */
    public Asset() {
    }


    /**
     * Getters and setters below.
     */
    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public CurrencyUSD getValue() {
        return value;
    }

    public void setValue(CurrencyUSD value) {
        this.value = value;
    }
}
