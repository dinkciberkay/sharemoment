import axios from "axios";

export const userLogin = creds => {
    return axios.post('/api/auth', {}, {
        auth: { //Basic auth headeri ile gitmek istiyoruz. Post Requestinin 3.parametresi configuration.
            //Bu configuration parametrelerinden biri "auth".
            //Böyle bir request attığımızda, request içerisinde
            // Basic Authentication Credentials'larını görüyor olacağız.
            username: creds.userName,
            password: creds.password
        }
    });
};