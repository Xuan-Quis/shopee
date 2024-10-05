import { configureStore } from "@reduxjs/toolkit";
import userReducer from "../reducer/user.reducer";
import categoriesReducer from "../reducer/category.reducer";

export const store = configureStore({
  reducer: {
    user: userReducer,
    categories: categoriesReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;

export type AppDispatch = typeof store.dispatch;
