from app.models.enums.crew_roles import CrewRoles
from app.models.enums.availabilities import CrewAvailibility
from app.models.enums.cert_issuers import CertIssuers, CertIssuingCountries

# TODO make enums be read from data file, and make a validation function


class EnumService:
    @staticmethod
    def get_crew_roles():
        return [CrewRoles.value for role in CrewRoles]

    @staticmethod
    def get_crew_availability():
        return [CrewAvailibility.value for availability in CrewAvailibility]

    @staticmethod
    def get_cert_issuers():
        return [CertIssuers.value for issuer in CertIssuers]

    @staticmethod
    def get_cert_countries():
        return [CertIssuingCountries.value for issuer in CertIssuingCountries]
