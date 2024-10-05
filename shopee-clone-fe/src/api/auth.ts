import axios from "axios";

const urlAuth = "";
const login = async (email: string, password: string): Promise<any> => {
  console.log(email, password);

  try {
    const res = await axios.post(urlAuth, {
      email: email,
      password: password,
    });
    return res.data;
  } catch (error) {
    console.log(error);
  }
};

const register = async (email: string, password: string): Promise<any> => {
  console.log(email, password);

  try {
    const res = await axios.post(urlAuth, {
      email: email,
      password: password,
    });
    return res.data;
  } catch (error) {
    console.log(error);
  }
};
export { login, register };
