import { Controller, FieldValues, useForm } from "react-hook-form";

import { zodResolver } from "@hookform/resolvers/zod";
import { z, ZodSchema } from "zod";

const loginSchema: ZodSchema = z.object({
  email: z
    .string({ required_error: "This field is required" })
    .min(1, "This field is required")
    .email("Please enter a valid e-mail address"),
  password: z.string({ required_error: "This field is required" }).min(1, "This field is required"),
});

type LoginFormData = z.infer<typeof loginSchema>;

const Form = () => {
  const {
    handleSubmit,
    control,
    formState: { errors },
  } = useForm<LoginFormData>({
    resolver: zodResolver(loginSchema),
    defaultValues: {
      email: "",
      password: "",
    },
  });

  const FormSubmitAction = async (formdata: FieldValues) => {
    try {
      // handle submit functions
    } catch (error) {
      // handle errors
    }
  };

  return (
    <form noValidate onSubmit={handleSubmit(FormSubmitAction)}>
      <Controller
        name="email"
        control={control}
        render={({ field }) => {
          return (
            /* add custom inputs */
            <input {...field} />
          );
        }}
      />
      <Controller
        name="password"
        control={control}
        render={({ field }) => {
          return (
            /* add custom inputs */
            <input type="password" {...field} />
          );
        }}
      />
      <button type="submit">Login</button>
    </form>
  );
};

export default Form;
