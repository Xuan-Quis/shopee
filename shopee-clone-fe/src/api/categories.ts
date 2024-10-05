import axios from "axios";

const getCategories = async (): Promise<any> => {
  try {
    const res = await axios.get("http://localhost:8080/api/public/categories");
    console.log(res.data);
    return res.data;
  } catch (error) {
    console.log(error);
  }
};

const createCategory = async (name: string): Promise<any> => {
  try {
    const res = await axios.post("http://localhost:8080/api/admin/categories", {
      name: name,
    });
    console.log("day la ", name);
    console.log(res.data);
    return res.data;
  } catch (error) {
    console.log(error);
  }
};

export { getCategories, createCategory };
