package com.ishaihachlili.nano.movies.Api;

import com.ishaihachlili.nano.movies.Api.Model.*;

import retrofit.RestAdapter;

/**
 * Created by Ishai on 7/23/2015.
 */
public class MoviesApi {
    private final String LOG_TAG = MoviesApi.class.getSimpleName();

    private final String BaseUrl = "http://api.themoviedb.org/3/";
    private final String ApiKey = "123456";

    public MovieItemModel[] GetMovies(String sortBy){
        MovieResultsModel result = getApi().movies(sortBy, ApiKey);

        return result.getMovies();
    }

    public MovieDetailsModel GetMovieDetails(Integer movieId){
        MovieDetailsModel result = getApi().movieDetails(movieId, ApiKey);

        return result;
    }

    private IMoviesDbApi getApi() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BaseUrl)  //call your base url
                .build();

        IMoviesDbApi api = restAdapter.create(IMoviesDbApi.class);
        return api;
    }
//
//    public MovieItemModel[] GetMovies(String sortBy){
//        HttpURLConnection urlConnection = null;
//        BufferedReader reader = null;
//
//        String moviesJson = null;
//        ///discover/movie?sort_by=popularity.desc
//        try {
//            final String MOVIES_BASE_URL =
//                    "http://api.themoviedb.org/3/discover/movie?";
//            final String APIKEY_PARAM = "api_key";
//            final String SORT_PARAM = "sort_by";
//
//            Uri builtUri = Uri.parse(MOVIES_BASE_URL).buildUpon()
//                    .appendQueryParameter(SORT_PARAM, sortBy)
//                    .appendQueryParameter(APIKEY_PARAM, ApiKey)
//                    .build();
//
//            URL url = new URL(builtUri.toString());
//
//            urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.setRequestMethod("GET");
//            urlConnection.connect();
//
//            // Read the input stream into a String
//            InputStream inputStream = urlConnection.getInputStream();
//            StringBuffer buffer = new StringBuffer();
//            if (inputStream == null) {
//                // Nothing to do.
//                return null;
//            }
//            reader = new BufferedReader(new InputStreamReader(inputStream));
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
//                // But it does make debugging a *lot* easier if you print out the completed
//                // buffer for debugging.
//                buffer.append(line + "\n");
//            }
//
//            if (buffer.length() == 0) {
//                // Stream was empty.  No point in parsing.
//                return null;
//            }
//            moviesJson = buffer.toString();
//        } catch (IOException e) {
//            Log.e(LOG_TAG, "Error ", e);
//            return null;
//        } finally {
//            if (urlConnection != null) {
//                urlConnection.disconnect();
//            }
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (final IOException e) {
//                    Log.e(LOG_TAG, "Error closing stream", e);
//                }
//            }
//        }
//
//        try {
//            return getMoviesDataFromJson(moviesJson);
//        } catch (JSONException e) {
//            Log.e(LOG_TAG, e.getMessage(), e);
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    private MovieItemModel[] getMoviesDataFromJson(String moviesJson)
//            throws JSONException {
//
//        // These are the names of the JSON objects that need to be extracted.
//        final String TMDB_LIST = "results";
//        final String TMDB_POSTERPATH = "poster_path";
//        final String TMDB_MOVIEID = "id";
//
//        JSONObject moviesJsonObject = new JSONObject(moviesJson);
//        JSONArray moviesArray = moviesJsonObject.getJSONArray(TMDB_LIST);
//
//        MovieItemModel[] resultMovies = new MovieItemModel[moviesArray.length()];
//
//        for(int i = 0; i < moviesArray.length(); i++) {
//            // Get the JSON object representing the movie
//            JSONObject movie = moviesArray.getJSONObject(i);
//            resultMovies[i] = new MovieItemModel(movie.getString(TMDB_POSTERPATH), movie.getInt(TMDB_MOVIEID));
//        }
//        return resultMovies;
//
//    }

//    public MovieItemModel GetMovieDetails(Integer movieId){
//        HttpURLConnection urlConnection = null;
//        BufferedReader reader = null;
//
//        String moviesJson = null;
//        ///discover/movie?sort_by=popularity.desc
//        try {
//            final String MOVIES_BASE_URL =
//                    "http://api.themoviedb.org/3//movie/";
//            final String APIKEY_PARAM = "api_key";
//            final String MOVIE_ID = "sort_by";
//
//            Uri builtUri = Uri.parse(MOVIES_BASE_URL).buildUpon()
//                    .appendPath(movieId.toString())
//                    .appendQueryParameter(APIKEY_PARAM, ApiKey)
//                    .build();
//
//            URL url = new URL(builtUri.toString());
//
//            urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.setRequestMethod("GET");
//            urlConnection.connect();
//
//            // Read the input stream into a String
//            InputStream inputStream = urlConnection.getInputStream();
//            StringBuffer buffer = new StringBuffer();
//            if (inputStream == null) {
//                // Nothing to do.
//                return null;
//            }
//            reader = new BufferedReader(new InputStreamReader(inputStream));
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
//                // But it does make debugging a *lot* easier if you print out the completed
//                // buffer for debugging.
//                buffer.append(line + "\n");
//            }
//
//            if (buffer.length() == 0) {
//                // Stream was empty.  No point in parsing.
//                return null;
//            }
//            moviesJson = buffer.toString();
//        } catch (IOException e) {
//            Log.e(LOG_TAG, "Error ", e);
//            return null;
//        } finally {
//            if (urlConnection != null) {
//                urlConnection.disconnect();
//            }
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (final IOException e) {
//                    Log.e(LOG_TAG, "Error closing stream", e);
//                }
//            }
//        }
//
//        try {
//            return getMovieDetailsDataFromJson(moviesJson);
//        } catch (JSONException e) {
//            Log.e(LOG_TAG, e.getMessage(), e);
//            e.printStackTrace();
//        }
//
//        return null;
//
//    }
}
