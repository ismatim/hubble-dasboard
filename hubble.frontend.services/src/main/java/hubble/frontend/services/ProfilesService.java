package hubble.frontend.services;

import hubble.frontend.models.Profile;
import hubble.frontend.repositories.ProfilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ProfilesService {

    @Autowired
    private ProfilesRepository profilesRepository;

    public Collection<Profile> findAllProfiles(){
        List<Profile> profiles = new ArrayList<>();
        for (Profile profile : profilesRepository.findAll()) {
            profiles.add(profile);
        }

        return profiles;

        }

    public void deleteProfile(int id) {
        profilesRepository.delete(id);

    }

    public Profile findProfileById(int id){
        return profilesRepository.findOne(id);
    }

    public int findProfileIdByName(String name){

        Collection<Profile> allProfiles = findAllProfiles();
        Profile profile;

        profile = allProfiles.parallelStream()
                .filter(x -> name.equals(x.getProfileName()))   // Buscamos por nombre
                .findAny()                                      // Si encuentra uno devuelve el objeto encontrado
                .orElse(null);                          //Si no encuentra nada devuelve null

        if(profile!=null)
            return profile.getId();
        else
            return -1;

    }

    public String findProfileDisplayName(String name){
        Collection<Profile> allProfiles = findAllProfiles();
        Profile profile;

       profile = allProfiles.parallelStream()
                .filter(x -> name.equals(x.getProfileName()))        // Buscamos por nombre
                .findAny()                                      // Si encuentra uno devuelve el objeto encontrado
                .orElse(null);                          //Si no encuentra nada devuelve null

        if(profile!=null)
            return profile.getDisplayName();
        else
            return null;

    }

    public void createProfile(Profile profile){
        profilesRepository.save(profile);
    }

    public Profile updateProfile(int id, String profileName, String displayName){
        Profile profile = findProfileById(id);
        profile.setProfileName(profileName);
        profile.setDisplayName(displayName);
        profilesRepository.save(profile);
        return profile;
    }
}
