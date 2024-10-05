import { useMutation, useQueryClient } from "@tanstack/react-query";
import React, { useState } from "react";
import { createCategory } from "../../api/categories";

const CreateComponent = () => {
  const queryClient = useQueryClient();

  const [nameCate, setNameCate] = useState("");

  const createCategoryMutation = useMutation({
    mutationFn: async ({ name }: any) => await createCategory(nameCate),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["categories"] });
    },

    onError: (error) => {
      console.error("Error liking blog:", error);
    },
  });

  const handleClick = () => {
    console.log("da bam", nameCate);
    createCategoryMutation.mutate(nameCate);
  };

  return (
    <div>
      <label htmlFor="">Name</label>
      <input
        onChange={(e) => setNameCate(e.currentTarget.value)}
        type="text"
        name=""
        id=""
      />
      <button
        onClick={(e) => {
          e.preventDefault();
          handleClick();
        }}
      >
        Add
      </button>
    </div>
  );
};

export default CreateComponent;
