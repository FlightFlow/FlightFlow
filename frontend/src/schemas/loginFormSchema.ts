import i18next from "i18next";
import { z, ZodSchema } from "zod";

export const loginFormSchema: ZodSchema = z.object({
  username: z
    .string({ required_error: i18next.t("schemas.requiredError") })
    .refine((value) => value.trim() !== "", {
      message: i18next.t("schemas.requiredError"),
    }),
  email: z
    .string({ required_error: i18next.t("schemas.requiredError") })
    .email({ message: i18next.t("schemas.invalidEmailError") })
    .refine((value) => value.trim() !== "", {
      message: i18next.t("schemas.requiredError"),
    }),
  password: z
    .string({ required_error: i18next.t("schemas.requiredError") })
    .refine((value) => value.trim() !== "", {
      message: i18next.t("schemas.requiredError"),
    }),
});

export type LoginFormSchemaParams = z.infer<typeof loginFormSchema>;
