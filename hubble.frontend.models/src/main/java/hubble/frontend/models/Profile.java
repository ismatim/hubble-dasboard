package hubble.frontend.models;



import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Entity(name = "hpbac_profiles")
@Table
public class Profile {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank( message = "{NotEmpy.sources.hpbac.profile.name}" )
    @Column(name = "name")
    private String profileName;

    @NotBlank( message = "Debe colocar un nombre de visualización" )
    @Column(name = "display_name")
    private String displayName;

    //TODO: Realizar la conexión con el id de fuente de datos
    @Column(name = "source_id")
    private int sourceId;

    public Profile(String profileName, String displayName) {
        this.profileName = profileName;
        this.displayName = displayName;
    }

    public Profile() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", profileName='" + profileName + '\'' +
                ", displayName='" + displayName + '\'' +
                ", sourceId=" + sourceId +
                '}';
    }
}
