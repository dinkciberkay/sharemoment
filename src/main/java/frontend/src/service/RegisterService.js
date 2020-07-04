import axios from "axios";

export const userRegister = (body) => {
    axios.post('/api/users', body);
};

