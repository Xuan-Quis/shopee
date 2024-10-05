import { createAsyncThunk, createReducer } from "@reduxjs/toolkit";
import axios from "axios";
import { getCategories } from "../api/categories";

const initialState = {
  isLoading: false,
  categories: [],
};

export const getCategoriesReducer = createAsyncThunk(
  "/categories/getCategoriesReducer",
  async () => {
    const response = await getCategories();
    console.log(response);
    return response;
  }
);

const categoriesReducer = createReducer(initialState, (build) => {
  build
    .addCase(getCategoriesReducer.pending, (state) => {
      state.isLoading = true;
      console.log("Fetching categories..."); // Kiểm tra khi pending
    })
    .addCase(getCategoriesReducer.fulfilled, (state, action) => {
      state.isLoading = false;
      state.categories = action.payload;
      console.log("Categories fetched successfully:", action.payload); // Kiểm tra khi thành công
    })
    .addCase(getCategoriesReducer.rejected, (state) => {
      state.isLoading = false;
      state.categories = [];
      console.error("Failed to fetch categories."); // Kiểm tra khi thất bại
    });
});

export default categoriesReducer;
