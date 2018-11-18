public JSONObject userTemplate(String firstName, String lastName, String email, String timeZone) {
        JSONObject json = new JSONObject()
            .put("firstName", firstName)
            .put("lastName", lastName)
            .put("email", email)
            .put("timeZone", "Europe/Amsterdam")
        return json;
    }

    public String createUser(String firstName, String lastName, String email) {
        JSONObject json = userTemplate(firstName, lastName, email)
            .put("newPassword", PASSWORD)
            .put("confirmPassword", PASSWORD);
        ClientResponse response = put(USER_API, json.toString());
        return new JSONObject(response.getEntity(String.class)).getString("id");
    }

    public String updateUser(JSONObject user, Map<String, String> fieldsToUpdate) {
        fieldsToUpdate.entrySet().forEach(entry -> user.put(entry.getKey(), entry.getValue()));
        ClientResponse response = put(USER_API, user.toString());
        return new JSONObject(response.getEntity(String.class)).getString("id");
    }

    public JSONObject findUserByEmail(String email) {
        ClientResponse response = get(USER_API + "email/" + email);
        return new JSONObject(response.getEntity(String.class));
    }
