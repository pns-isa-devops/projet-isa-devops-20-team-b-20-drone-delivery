package entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class Parcel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int parcelId;

    public int getParcelId() {
        return parcelId;
    }

    @Override
    public String toString() {
        return "Parcel " + this.parcelId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;

        if (obj instanceof Parcel) {
            Parcel d = (Parcel) obj;
            return this.parcelId == d.parcelId;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.parcelId);
    }
}
