import React from "react";

const CategoryItem = ({ data }: any) => {
  console.log(data);
  return (
    <div className="flex flex-col items-center p-2 bg-white  shadow hover:shadow-md transition-shadow">
      {/* Nơi chứa từng danh mục */}
      <span className="text-3xl mb-2">
        <img
          src="https://down-vn.img.susercontent.com/file/31234a27876fb89cd522d7e3db1ba5ca@resize_w320_nl.webp"
          alt=""
        />
      </span>
      <span className="text-center text-xs">{data.name}</span>
    </div>
  );
};

export default CategoryItem;
