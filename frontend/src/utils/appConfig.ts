import { z, ZodSchema } from "zod";

import UtilTypes from "@/types/utils";

import Logger from "./logger";

const envSchema: ZodSchema = z.object({
  VITE_APP_ENVIRONMENT: z.enum(["DEV", "TEST", "PROD"]).default("DEV"),
  VITE_APP_SERVER_URL: z.string().default("localhost"),
  VITE_APP_SERVER_PORT: z
    .string()
    .transform((value) => parseInt(value, 10))
    .default("8081"),
  VITE_APP_SERVER_API_VERSION: z.string().regex(/v.*/g),
  VITE_APP_AUTH0_DOMAIN: z.string().min(1),
  VITE_APP_AUTH0_CLIENT_ID: z.string().min(1),
  VITE_APP_AUTH0_CALLBACK_URL: z.string().min(1),
});

const parsedEnvSchema = envSchema.safeParse(import.meta.env);

if (!parsedEnvSchema.success) {
  Logger.error(`Cannot parse .env: ${parsedEnvSchema.error}`);
}

export default parsedEnvSchema.data as UtilTypes.AppConfigTypes.EnvProps;
