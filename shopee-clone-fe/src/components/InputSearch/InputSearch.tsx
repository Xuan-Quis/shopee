import React from "react";
import { CiSearch } from "react-icons/ci";
interface Props {
  className?: string;
}

const InputSearch = ({ className }: Props) => {
  return (
    <div className={className}>
      <form className=" mx-auto">
        <label
          htmlFor="default-search"
          className="mb-2 text-sm font-medium text-gray-900 sr-only dark:text-white"
        >
          Search
        </label>
        <div className="relative flex">
          <input
            type="search"
            id="default-search"
            className="block w-full p-2 ps-5  text-gray-900 border border-gray-300 rounded bg-gray-50 focus:ring-blue-500 focus:border-blue-500   dark:placeholder-gray-400  dark:focus:ring-blue-500 dark:focus:border-blue-500"
            placeholder="Sale quốc tế"
            required
          />
          <button
            type="submit"
            className="text-white absolute end-2.5 bottom-1 bg-[#FB5533] hover:bg-[#fb6445] focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded text-sm px-4 py-2 "
          >
            <CiSearch className="text-white text-xl" />
          </button>
        </div>
      </form>
    </div>
  );
};

export default InputSearch;
