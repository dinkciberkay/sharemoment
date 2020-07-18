import axios from "axios";

export const userRegister = body => {
    return axios.post('/api/users', body);
};

