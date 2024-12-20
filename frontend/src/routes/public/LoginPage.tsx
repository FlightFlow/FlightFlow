import { useState } from "react";
import { Controller, FieldValues, useForm } from "react-hook-form";
import { useTranslation } from "react-i18next";
import { Link as RouterLink } from "react-router";

import { loginFormSchema, LoginFormSchemaParams } from "@/schemas/loginFormSchema";
import ComponentTypes from "@/types/components";
import { zodResolver } from "@hookform/resolvers/zod";
import { Divider, TextField } from "@mui/material";

import FormHeader from "@/components/FormHeader";
import Snackbar from "@/components/Snackbar";
import SubmitButton from "@/components/SubmitButton";

import useLoginMutation from "@/hooks/auth/useLoginMutation";

import { authFormElementStyles } from "@/styles/authPage.style";
import { linkStyles } from "@/styles/global.style";

const LoginPage = () => {
  const { t } = useTranslation();
  const { mutateAsync: loginMutateAsync } = useLoginMutation();
  const [dataState, setDataState] = useState<ComponentTypes.DataStateProps>({
    snackbarState: { isOpen: false, message: "" },
    isLoading: false,
    isSuccess: false,
  });

  const {
    handleSubmit,
    control,
    formState: { errors },
  } = useForm<LoginFormSchemaParams>({
    resolver: zodResolver(loginFormSchema),
    defaultValues: { username: "", email: "", password: "" },
  });

  const FormSubmitAction = async (formdata: FieldValues) => {
    setDataState((currentState) => ({ ...currentState, isLoading: true }));
    const response = await loginMutateAsync({
      username: formdata.username,
      email: formdata.email,
      password: formdata.password,
    });
    return setDataState(() => ({
      snackbarState: { isOpen: true, message: response.message },
      isLoading: false,
      isSuccess: response.isSuccess ? true : false,
    }));
  };

  return (
    <>
      <FormHeader
        title={t("forms.titles.loginTitle")}
        subtitle={t("forms.subtitles.loginSubtitle")}
      />
      <form noValidate onSubmit={handleSubmit(FormSubmitAction)} style={authFormElementStyles}>
        <Controller
          name="username"
          control={control}
          render={({ field }) => {
            return (
              <TextField
                {...field}
                variant="outlined"
                label={t("forms.labels.username")}
                error={!!errors.username}
                helperText={(errors.username?.message as string) || ""}
                size="small"
                fullWidth
              />
            );
          }}
        />
        <Controller
          name="email"
          control={control}
          render={({ field }) => {
            return (
              <TextField
                {...field}
                variant="outlined"
                label={t("forms.labels.email")}
                error={!!errors.email}
                helperText={(errors.email?.message as string) || ""}
                size="small"
                fullWidth
              />
            );
          }}
        />
        <Controller
          name="password"
          control={control}
          render={({ field }) => {
            return (
              <TextField
                {...field}
                variant="outlined"
                label={t("forms.labels.password")}
                error={!!errors.password}
                helperText={(errors.password?.message as string) || ""}
                size="small"
                type="password"
                fullWidth
              />
            );
          }}
        />
        <SubmitButton
          isLoading={dataState.isLoading}
          isSuccess={dataState.isSuccess}
          label={t("forms.buttons.loginButton")}
        />
      </form>
      <Divider />
      <RouterLink to={"/register"} style={linkStyles}>
        {t("forms.bottomTexts.loginBottomText")}
      </RouterLink>
      <Snackbar
        snackbarState={dataState}
        setSnackbarState={setDataState}
        severity={dataState.isSuccess ? "success" : "error"}
      />
    </>
  );
};

export default LoginPage;
