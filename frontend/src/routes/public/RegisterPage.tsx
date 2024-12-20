import { useState } from "react";
import { Controller, FieldValues, useForm } from "react-hook-form";
import { useTranslation } from "react-i18next";
import { Link as RouterLink } from "react-router";

import { registerFormSchema, RegisterFormSchemaParams } from "@/schemas/registerFormSchema";
import ComponentTypes from "@/types/components";
import { zodResolver } from "@hookform/resolvers/zod";
import { Divider, TextField } from "@mui/material";

import FormHeader from "@/components/FormHeader";
import Snackbar from "@/components/Snackbar";
import SubmitButton from "@/components/SubmitButton";

import useRegisterMutation from "@/hooks/auth/useRegisterMutation";

import { authFormElementStyles } from "@/styles/authPage.style";
import { linkStyles } from "@/styles/global.style";

const RegisterPage = () => {
  const { t } = useTranslation();
  const { mutateAsync: registerMutateAsync } = useRegisterMutation();
  const [dataState, setDataState] = useState<ComponentTypes.DataStateProps>({
    snackbarState: { isOpen: false, message: "" },
    isLoading: false,
    isSuccess: false,
  });

  const {
    handleSubmit,
    control,
    formState: { errors },
  } = useForm<RegisterFormSchemaParams>({
    resolver: zodResolver(registerFormSchema),
    defaultValues: { fullname: "", username: "", email: "", password: "", passwordAgain: "" },
  });

  const FormSubmitAction = async (formdata: FieldValues) => {
    if (formdata.password !== formdata.passwordAgain) {
      return setDataState(() => ({
        snackbarState: { isOpen: true, message: t("forms.errors.notMatchingPasswords") },
        isLoading: false,
        isSuccess: false,
      }));
    }
    setDataState((currentState) => ({ ...currentState, isLoading: true }));
    const response = await registerMutateAsync({
      fullName: formdata.fullName,
      username: formdata.username,
      email: formdata.email,
      password: formdata.password,
      passwordAgain: formdata.passwordAgain,
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
        title={t("forms.titles.registerTitle")}
        subtitle={t("forms.subtitles.registerSubtitle")}
      />
      <form noValidate onSubmit={handleSubmit(FormSubmitAction)} style={authFormElementStyles}>
        <Controller
          name="fullName"
          control={control}
          render={({ field }) => {
            return (
              <TextField
                {...field}
                variant="outlined"
                label={t("forms.labels.fullName")}
                error={!!errors.fullName}
                helperText={(errors.fullName?.message as string) || ""}
                size="small"
                fullWidth
              />
            );
          }}
        />
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
        <Controller
          name="passwordAgain"
          control={control}
          render={({ field }) => {
            return (
              <TextField
                {...field}
                variant="outlined"
                label={t("forms.labels.passwordAgain")}
                error={!!errors.passwordAgain}
                helperText={(errors.passwordAgain?.message as string) || ""}
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
          label={t("forms.buttons.registerButton")}
        />
      </form>
      <Divider />
      <RouterLink to={"/login"} style={linkStyles}>
        {t("forms.bottomTexts.registerBottomText")}
      </RouterLink>
      <Snackbar
        snackbarState={dataState}
        setSnackbarState={setDataState}
        severity={dataState.isSuccess ? "success" : "error"}
      />
    </>
  );
};

export default RegisterPage;
