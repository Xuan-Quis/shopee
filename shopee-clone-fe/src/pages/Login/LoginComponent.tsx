import React, { useState } from "react";
import { FaFacebook, FaGoogle } from "react-icons/fa";
import ButtonComponent from "../../components/ButtonComponent/ButtonComponent";
import InputComponent from "../../components/InputComponent/InputComponent";
import { Link, useNavigate } from "react-router-dom";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import { useDispatch } from "react-redux";

const LoginComponent = () => {
  // const dispatch = useDispatch();

  // const navigate = useNavigate();

  // const queryClient = useQueryClient();

  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");

  const handleEmailChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setEmail(event.target.value);
    console.log("Email:", event.target.value);
  };
  const handlePasswordChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(event.target.value);
    console.log("Password:", event.target.value);
  };

  const handleButtonClick = (event: React.MouseEvent<HTMLButtonElement>) => {
    console.log("Email  đăng nhập", email);
    console.log("Password  đăng nhập", password);
  };

  // xử lý đăng nhập với reactQuery

  // const loginMutation = useMutation({
  //   mutationFn: async ({ email, password }: any) =>
  //     await login(email, password),
  //   onSuccess: async (data) => {
  //     console.log(data);
  //     if (data ) {
  //       queryClient.invalidateQueries({ queryKey: ["user"] });
  //       await dispatch(checkIsAuthenticated(true));
  //       await dispatch(addUser(data));

  //       navigate("/");
  //     }
  //   },
  // });

  return (
    <>
      <div className="bg-[#fff] my-16 w-96 py-8 px-7 rounded">
        <h3 className="text-[#222] text-xl">Đăng nhập</h3>

        <div className="pt-8 flex flex-col gap-y-9">
          <InputComponent
            handleChange={handleEmailChange}
            placeholder="Email/Số điện thoại/Tên đăng nhập"
            type="text"
          />

          <InputComponent
            handleChange={handlePasswordChange}
            placeholder="Mật khẩu"
            type="password"
          />

          <ButtonComponent
            handleOnClick={handleButtonClick}
            style={{ backgroundColor: "#ee4d2d" }}
          >
            Đăng nhập
          </ButtonComponent>
        </div>

        <div className="flex items-center gap-4 pt-3 pb-4 ">
          <div className="border-solid border-t-2 border-[#dbdbdb] w-full"></div>
          <span className="text-sm uppercase text-[#ccc]">hoặc</span>
          <div className="border-solid border-t-2 border-[#dbdbdb] w-full"></div>
        </div>

        <div className="grid grid-cols-2 gap-2">
          <ButtonComponent
            style={{
              backgroundColor: "#fff",
              color: "#000",
              border: "1px solid #ccc",
              flex: 1,
            }}
          >
            <FaFacebook />
            <span>Facebook</span>
          </ButtonComponent>

          <ButtonComponent
            style={{
              backgroundColor: "#fff",
              color: "#000",
              border: "1px solid #ccc",
              flex: 1,
            }}
          >
            <FaGoogle />
            <span>Google</span>
          </ButtonComponent>
        </div>

        <div
          className="text-sm"
          style={{ padding: "20px", textAlign: "center" }}
        >
          <span style={{ color: "rgba(0, 0, 0, .26)" }}>
            Bạn mới biết đến Shopee?
          </span>
          <Link to="/register">
            <span className="text-[#ee4d2d] ml-1">Đăng ký</span>
          </Link>
        </div>
      </div>
    </>
  );
};

export default LoginComponent;
