import React from "react";

interface Props {
  placeholder: string;
  type?: string;
  handleChange?: (event: React.ChangeEvent<HTMLInputElement>) => void; // Kiểu chính xác cho handleChange
}

const InputComponent = ({ placeholder, type, handleChange }: Props) => {
  return (
    <>
      <input
        onChange={handleChange} // Gọi hàm handleChange khi có sự thay đổi
        id="price"
        name="price"
        type={type}
        placeholder={placeholder}
        className="block w-full border-0 p-2 text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-300 focus:border-[rgba(0, 0, 0, .54)] sm:text-sm sm:leading-6"
      />
    </>
  );
};

export default InputComponent;
